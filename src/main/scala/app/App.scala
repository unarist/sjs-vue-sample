package app

import facade.vue
import scalajs.js
import org.scalajs.dom
import scala.scalajs.js.|

object App {
  def main(args: Array[String]): Unit = {
    vue.Vue.component("comp-normal", vue.ComponentOptions(
      props = js.Dictionary(
        "strProp" -> vue.PropOptions(vue.PropType.String),
        "strProp2" -> vue.PropOptions(vue.PropType.String, default = "default value"),
        "boolProp" -> vue.PropOptions(vue.PropType.Boolean, default = false),
        "boolProp2" -> vue.PropOptions(vue.PropType.Boolean, default = false)
      ),
      data = js.ThisFunction.fromFunction1 { (vueInstance: vue.Vue) =>
        js.Dynamic.literal(clickCount = 0)
      },
      mounted = js.ThisFunction.fromFunction1 { (vueInstance: vue.Vue) =>
        println("comp-normal mounted")
        ()
      },
      watch = js.Dictionary(
        "strProp" -> vue.WatchHandler(js.ThisFunction.fromFunction2((v: vue.Vue, newValue: String) => println(""))),
        "boolProp" -> vue.WatchHandler((newValue: Boolean) => println("")),
        "clickCount" -> vue.WatchHandler(
          handler = (newValue: Int, oldValue: Int) => println(""),
          immediate = true
        )
      ),
      template =
      // language=HTML
        """
          |<div>
          |<h3>Component (class-based, template)</h3>
          |<p>strProp={{strProp}}, strProp2={{strProp2}}, boolProp={{boolProp}}, boolProp2={{boolProp2}}</p>
          |<p>clickCount={{clickCount}} <button v-on:click='clickCount = clickCount + 1'>click</button></p>
          |<div><slot></slot></div>
          |</div>
          |""".stripMargin
    ))
    vue.Vue.component("comp-render", vue.ComponentOptions(
      props = js.Dictionary(
        "strProp" -> vue.PropOptions(vue.PropType.String),
        "boolProp" -> vue.PropOptions(vue.PropType.Boolean, default = false),
      ),
      data = js.ThisFunction.fromFunction1 { (vueInstance: vue.Vue) =>
        js.Dynamic.literal(clickCount = 0)
      },
      render = js.ThisFunction.fromFunction2 { (vueInstance: vue.Vue, h: vue.CreateElement) => {
        h("div", js.Array(
          h("h3", "Component (class-based, render function)"),
          h("p", s"strProp=${vueInstance.$props.strProp}, boolProp=${vueInstance.$props.boolProp}"),
          // まあこのケースはそんなに多くない気もするし、型引数指定は諦めるかなあ…
          h("p", js.Array[String | vue.VNode](
            s"clickCount=${vueInstance.$data.clickCount} ",
            h("button", vue.VNodeData(
              // js.defined で包むか、js.Dictionaryに型引数を書くか、どちらかが必要
              // もしくはjs.Dynamic.literalを使う → comp-functional参照
              on = js.Dictionary[js.Function](
                "click" -> (() => vueInstance.$data.clickCount = vueInstance.$data.clickCount.asInstanceOf[Int] + 1)
              )
            ), "click")
          )),
          h("div", vueInstance.$slots.getOrElse("default", js.undefined))
        ))
      }
      }
    ))
    vue.Vue.component("comp-functional", vue.FunctionalComponentOptions(
      functional = true,
      props = js.Dictionary(
        "strProp" -> vue.PropOptions(vue.PropType.String),
        "boolProp" -> vue.PropOptions(vue.PropType.Boolean, default = false),
      ),
      render = js.defined { (h, ctx) => {
        h("div", js.Array(
          h("h3", "Component (functional, render function)"),
          h("p", s"strProp=${ctx.props.strProp}, boolProp=${ctx.props.boolProp}"),
          h("button", vue.VNodeData(
            on = js.Dynamic.literal(
              click = () => dom.window.alert("clicked")
            )
          ), "alert"),
          h("div", ctx.slots().getOrElse("default", js.undefined))
        ))
      }
      }
    ))
    new vue.Vue(vue.ComponentOptions(
      template =
      // language=HTML
        """
          |<div>
          |  <h2>Vue x ScalaJS</h2>
          |  <comp-normal str-prop='hoge' bool-prop>hello</comp-normal>
          |  <comp-render str-prop='hoge' bool-prop>hello</comp-render>
          |  <comp-functional str-prop='hoge' bool-prop>hello</comp-functional>
          |</div>
          |""".stripMargin
    )).$mount(dom.document.getElementById("root"))
  }
}
