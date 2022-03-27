package facade

import org.scalajs.dom.{Element, HTMLElement, Node}
import scala.scalajs.js
import js.annotation._
import js.|

package object vue {
  type SingleArray[T] = js.Array[T]
  type CombinedVueInstance = Vue
  // VueConstructorが object Vue っぽかったのでそうしたけど、どうなんだ？
  type VueConstructor = Vue.type
  type ExtendedVue = VueConstructor
  type PluginFunction[T] = js.Function2[VueConstructor, T, Unit]
  type ScopedSlot = js.Function1[js.Any, ScopedSlotReturnValue]
  //  type ScopedSlotReturnValue = js.UndefOr[VNode | String | Boolean | Null | ScopedSlotReturnArray]
  type ScopedSlotReturnValue = VNode | js.Array[VNode]
  type NormalizedScopedSlot = js.Function1[js.Any, js.Array[VNode]]
  // Vue#$createElementをJSX互換にするためにここまで広げていた雰囲気を感じる
  // https://jp.vuejs.org/v2/guide/render-function.html#JSX
  // type VNodeChildrenContents = js.UndefOr[js.ArrayVNode | SingleArray[ScopedSlot] | String | Boolean | Null]
  type VNodeChildren = js.UndefOr[String | js.Array[VNode] | js.Array[String | VNode]]
  type Component = VueConstructor | FunctionalComponentOptions | ComponentOptions
  type WatchHandler[T] = String | js.Function2[T, T, Unit]
  type WatchItem = WatchOptionsWithHandler[js.Any] | WatchHandler[Any]
  type DirectiveFunction = (HTMLElement, DirectiveBinding, VNode, VNode) => Unit
  type InjectKey = String | js.Symbol
  type InjectOptions = js.Dictionary[InjectKey | js.Any] | js.Array[String]

  @js.native
  trait CreateElement extends js.Object {
    // d.tsだと2行にまとめてあったが、オーバーロード解決が死ぬのでバラす
    def apply(): VNode = js.native
    def apply(tag: String): VNode = js.native
    def apply(tag: String, children: VNodeChildren): VNode = js.native
    def apply(tag: String, data: VNodeData, children: VNodeChildren = ???): VNode = js.native
  }

  @JSGlobal
  @js.native
  class Vue(options: js.UndefOr[ComponentOptions]) extends js.Object {
    def $el: Element = js.native
    def $options: ComponentOptions = js.native
    def $parent: Vue = js.native
    def $root: Vue = js.native
    def $children: js.Array[Vue] = js.native
    def $refs: js.Dictionary[js.UndefOr[Vue | Element | js.Array[Vue | Element]]] = js.native
    // このUndefOrが存在しないキー向けなのかundefinedなslotが入っている可能性があるってことなのか、わからん
    def $slots: js.Dictionary[js.UndefOr[js.Array[VNode]]] = js.native
    def $scopedSlots: js.Dictionary[js.UndefOr[NormalizedScopedSlot]] = js.native
    def $isServer: Boolean = js.native
    def $data: js.Dynamic = js.native
    def $props: js.Dynamic = js.native
    def $ssrContext: js.Any = js.native
    def $vnode: VNode = js.native
    def $attrs: js.Dictionary[String] = js.native
    def $listeners: js.Dictionary[js.Function | js.Array[js.Function]] = js.native
    def $mount(elementOrSelector: Element | String = ???, hydrating: Boolean = ???): this.type = js.native
    def $forceUpdate(): Unit = js.native
    def $destroy(): Unit = js.native
    def $set[T](`object`: js.Object, key: String | Double, value: T): T = js.native
    def $set[T](array: js.Array[T], key: Double, value: T): T = js.native
    def $delete(`object`: js.Object, key: String | Double): Unit = js.native
    def $delete[T](array: js.Array[T], key: Double): Unit = js.native
    def $watch(exp: String, callback: js.ThisFunction2[this.type, js.Any, js.Any, Unit]): js.Function0[Unit] = js.native
    def $watch(exp: String, callback: js.ThisFunction2[this.type, js.Any, js.Any, Unit], options: WatchOptions): js.Function0[Unit] = js.native
    def $watch[T](fn: js.ThisFunction0[this.type, T], callback: js.ThisFunction2[this.type, T, T, Unit], options: WatchOptions = ???): js.Function0[Unit] = js.native
    def $on(event: String | js.Array[String], callback: js.Function): this.type = js.native
    def $once(event: String | js.Array[String], callback: js.Function): this.type = js.native
    def $off(event: String | js.Array[String] = ???, callback: js.Function = ???): this.type = js.native
    def $emit(event: String, args: js.Any*): this.type = js.native
    def $nextTick(callback: js.ThisFunction0[this.type, Unit]): Unit = js.native
    def $nextTick(): js.Promise[Unit] = js.native
    var $createElement: CreateElement = js.native
  }

  @JSGlobal
  @js.native
  object Vue extends js.Object {
    def extend(options: ComponentOptions = ???): ExtendedVue = js.native
    def nextTick[T](callback: js.ThisFunction0[T, Unit], context: T = ???): Unit = js.native
    def nextTick(): js.Promise[Unit] = js.native
    def set[T](`object`: js.Object, key: String | Double, value: T): T = js.native
    def set[T](array: js.Array[T], key: Double, value: T): T = js.native
    def delete(`object`: js.Object, key: String | Double): Unit = js.native
    def delete[T](array: js.Array[T], key: Double): Unit = js.native
    def directive(id: String, definition: DirectiveOptions | DirectiveFunction = ???): DirectiveOptions = js.native
    def filter(id: String, definition: js.Function = ???): js.Function = js.native
    def component(id: String): this.type = js.native
    def component(id: String, constructor: this.type): this.type = js.native
    def component(id: String, definition: Component = ???): this.type = js.native
    def use[T](plugin: PluginObject[T] | PluginFunction[T], options: T = ???): this.type = js.native
    def use(plugin: PluginObject[js.Any] | PluginFunction[js.Any], options: js.Any*): this.type = js.native
    def mixin(mixin: this.type | ComponentOptions): this.type = js.native
    def compile(template: String): js.Any = js.native
    def observable[T](obj: T): T = js.native
    var config: VueConfiguration = js.native
    var version: String = js.native
  }

  @js.native
  trait VueConfiguration extends js.Object {
    var silent: Boolean
    var optionMergeStrategies: js.Any
    var devtools: Boolean
    var productionTip: Boolean
    var performance: Boolean
    var errorHandler: js.Function3[js.Error, Vue, String, Unit]
    var warnHandler: js.Function3[String, Vue, String, Unit]
    var ignoredElements: js.Array[String | js.RegExp]
    var keyCodes: js.Dictionary[Double | js.Array[Double]]
    var async: Boolean
  }

  trait PluginObject[T] extends js.Object {
    var install: PluginFunction[T]
  }

  @js.native
  trait ScopedSlotReturnArray extends js.Array[ScopedSlotReturnValue] {
  }

  object ScopedSlotReturnArray {
  }

  @js.native
  trait VNodeChildrenArrayContents extends js.Array[VNodeChildren | VNode] {
  }

  object VNodeChildrenArrayContents {
  }

  @js.native
  trait VNode extends js.Object {
    var tag: js.UndefOr[String] = js.native
    var data: js.UndefOr[VNodeData] = js.native
    var children: js.UndefOr[js.Array[VNode]] = js.native
    var text: js.UndefOr[String] = js.native
    var elm: js.UndefOr[Node] = js.native
    var ns: js.UndefOr[String] = js.native
    var context: js.UndefOr[Vue] = js.native
    var key: js.UndefOr[String | Double | js.Symbol | Boolean] = js.native
    var componentOptions: js.UndefOr[VNodeComponentOptions] = js.native
    var componentInstance: js.UndefOr[Vue] = js.native
    var parent: js.UndefOr[VNode] = js.native
    var raw: js.UndefOr[Boolean] = js.native
    var isStatic: js.UndefOr[Boolean] = js.native
    var isRootInsert: Boolean = js.native
    var isComment: Boolean = js.native
  }

  @js.native
  trait VNodeComponentOptions extends js.Object {
    var Ctor: VueConstructor = js.native
    var propsData: js.UndefOr[js.Object] = js.native
    var listeners: js.UndefOr[js.Object] = js.native
    var children: js.UndefOr[js.Array[VNode]] = js.native
    var tag: js.UndefOr[String] = js.native
  }

  trait VNodeData extends js.Object {
    var key: js.UndefOr[String | Double]
    var slot: js.UndefOr[String]
    var scopedSlots: js.UndefOr[js.Dictionary[js.UndefOr[ScopedSlot]]]
    var ref: js.UndefOr[String]
    var refInFor: js.UndefOr[Boolean]
    var tag: js.UndefOr[String]
    var staticClass: js.UndefOr[String]
    var `class`: js.UndefOr[js.Any]
    var staticStyle: js.UndefOr[js.Dictionary[js.Any]]
    var style: js.UndefOr[String | js.Array[js.Object] | js.Object]
    var props: js.UndefOr[js.Dictionary[js.Any]]
    var attrs: js.UndefOr[js.Dictionary[js.Any]]
    var domProps: js.UndefOr[js.Dictionary[js.Any]]
    var hook: js.UndefOr[js.Dictionary[js.Function]]
    var on: js.UndefOr[js.Dictionary[js.Function | js.Array[js.Function]]]
    var nativeOn: js.UndefOr[js.Dictionary[js.Function | js.Array[js.Function]]]
    var transition: js.UndefOr[js.Object]
    var show: js.UndefOr[Boolean]
    var inlineTemplate: js.UndefOr[VNodeData.InlineTemplate]
    var directives: js.UndefOr[js.Array[VNodeDirective]]
    var keepAlive: js.UndefOr[Boolean]
  }

  object VNodeData {
    def apply(
      key: js.UndefOr[String | Double] = js.undefined,
      slot: js.UndefOr[String] = js.undefined,
      scopedSlots: js.UndefOr[js.Dictionary[js.UndefOr[ScopedSlot]]] = js.undefined,
      ref: js.UndefOr[String] = js.undefined,
      refInFor: js.UndefOr[Boolean] = js.undefined,
      tag: js.UndefOr[String] = js.undefined,
      staticClass: js.UndefOr[String] = js.undefined,
      `class`: js.UndefOr[js.Any] = js.undefined,
      staticStyle: js.UndefOr[js.Dictionary[js.Any]] = js.undefined,
      style: js.UndefOr[String | js.Array[js.Object] | js.Object] = js.undefined,
      props: js.UndefOr[js.Dictionary[js.Any]] = js.undefined,
      attrs: js.UndefOr[js.Dictionary[js.Any]] = js.undefined,
      domProps: js.UndefOr[js.Dictionary[js.Any]] = js.undefined,
      hook: js.UndefOr[js.Dictionary[js.Function]] = js.undefined,
      // 推論がしんどいので複数ハンドラ対応は捨てる（それでも js.defined か js.Dictionary の型引数どっちかは書かないといけない）
      // on: js.UndefOr[js.Dictionary[js.Function | js.Array[js.Function]]] = js.undefined,
      // nativeOn: js.UndefOr[js.Dictionary[js.Function | js.Array[js.Function]]] = js.undefined,
      on: js.UndefOr[js.Dictionary[js.Function] | js.Dynamic] = js.undefined,
      nativeOn: js.UndefOr[js.Dictionary[js.Function] | js.Dynamic] = js.undefined,
      transition: js.UndefOr[js.Object] = js.undefined,
      show: js.UndefOr[Boolean] = js.undefined,
      inlineTemplate: js.UndefOr[VNodeData.InlineTemplate] = js.undefined,
      directives: js.UndefOr[js.Array[VNodeDirective]] = js.undefined,
      keepAlive: js.UndefOr[Boolean] = js.undefined
    ): VNodeData = {
      val _obj$ = js.Dynamic.literal(
      )
      key.foreach(_v => _obj$.updateDynamic("key")(_v.asInstanceOf[js.Any]))
      slot.foreach(_v => _obj$.updateDynamic("slot")(_v.asInstanceOf[js.Any]))
      scopedSlots.foreach(_v => _obj$.updateDynamic("scopedSlots")(_v.asInstanceOf[js.Any]))
      ref.foreach(_v => _obj$.updateDynamic("ref")(_v.asInstanceOf[js.Any]))
      refInFor.foreach(_v => _obj$.updateDynamic("refInFor")(_v.asInstanceOf[js.Any]))
      tag.foreach(_v => _obj$.updateDynamic("tag")(_v.asInstanceOf[js.Any]))
      staticClass.foreach(_v => _obj$.updateDynamic("staticClass")(_v.asInstanceOf[js.Any]))
      `class`.foreach(_v => _obj$.updateDynamic("class")(_v.asInstanceOf[js.Any]))
      staticStyle.foreach(_v => _obj$.updateDynamic("staticStyle")(_v.asInstanceOf[js.Any]))
      style.foreach(_v => _obj$.updateDynamic("style")(_v.asInstanceOf[js.Any]))
      props.foreach(_v => _obj$.updateDynamic("props")(_v.asInstanceOf[js.Any]))
      attrs.foreach(_v => _obj$.updateDynamic("attrs")(_v.asInstanceOf[js.Any]))
      domProps.foreach(_v => _obj$.updateDynamic("domProps")(_v.asInstanceOf[js.Any]))
      hook.foreach(_v => _obj$.updateDynamic("hook")(_v.asInstanceOf[js.Any]))
      on.foreach(_v => _obj$.updateDynamic("on")(_v.asInstanceOf[js.Any]))
      nativeOn.foreach(_v => _obj$.updateDynamic("nativeOn")(_v.asInstanceOf[js.Any]))
      transition.foreach(_v => _obj$.updateDynamic("transition")(_v.asInstanceOf[js.Any]))
      show.foreach(_v => _obj$.updateDynamic("show")(_v.asInstanceOf[js.Any]))
      inlineTemplate.foreach(_v => _obj$.updateDynamic("inlineTemplate")(_v.asInstanceOf[js.Any]))
      directives.foreach(_v => _obj$.updateDynamic("directives")(_v.asInstanceOf[js.Any]))
      keepAlive.foreach(_v => _obj$.updateDynamic("keepAlive")(_v.asInstanceOf[js.Any]))
      _obj$.asInstanceOf[VNodeData]
    }

    trait InlineTemplate extends js.Object {
      var render: js.Function
      var staticRenderFns: js.Array[js.Function]
    }

    object InlineTemplate {
      def apply(
        render: js.Function,
        staticRenderFns: js.Array[js.Function]
      ): InlineTemplate = {
        val _obj$ = js.Dynamic.literal(
          "render" -> render.asInstanceOf[js.Any],
          "staticRenderFns" -> staticRenderFns.asInstanceOf[js.Any]
        )
        _obj$.asInstanceOf[InlineTemplate]
      }
    }
  }

  trait VNodeDirective extends js.Object {
    var name: String
    var value: js.UndefOr[js.Any]
    var oldValue: js.UndefOr[js.Any]
    var expression: js.UndefOr[String]
    var arg: js.UndefOr[String]
    var oldArg: js.UndefOr[String]
    var modifiers: js.UndefOr[js.Dictionary[Boolean]]
  }

  object VNodeDirective {
    def apply(
      name: String,
      value: js.UndefOr[js.Any] = js.undefined,
      oldValue: js.UndefOr[js.Any] = js.undefined,
      expression: js.UndefOr[String] = js.undefined,
      arg: js.UndefOr[String] = js.undefined,
      oldArg: js.UndefOr[String] = js.undefined,
      modifiers: js.UndefOr[js.Dictionary[Boolean]] = js.undefined
    ): VNodeDirective = {
      val _obj$ = js.Dynamic.literal(
        "name" -> name.asInstanceOf[js.Any]
      )
      value.foreach(_v => _obj$.updateDynamic("value")(_v.asInstanceOf[js.Any]))
      oldValue.foreach(_v => _obj$.updateDynamic("oldValue")(_v.asInstanceOf[js.Any]))
      expression.foreach(_v => _obj$.updateDynamic("expression")(_v.asInstanceOf[js.Any]))
      arg.foreach(_v => _obj$.updateDynamic("arg")(_v.asInstanceOf[js.Any]))
      oldArg.foreach(_v => _obj$.updateDynamic("oldArg")(_v.asInstanceOf[js.Any]))
      modifiers.foreach(_v => _obj$.updateDynamic("modifiers")(_v.asInstanceOf[js.Any]))
      _obj$.asInstanceOf[VNodeDirective]
    }
  }

  trait ComponentOptions extends js.Object {
    //    var data: js.UndefOr[js.Object | js.ThisFunction0[Vue, js.Object]]
    var data: js.UndefOr[js.ThisFunction0[Vue, js.Object] | js.Function0[js.Object]]
    var props: js.UndefOr[PropsDefinition]
    var propsData: js.UndefOr[js.Object | js.Dictionary[js.Any]]
    var computed: js.UndefOr[js.Dictionary[js.Function0[js.Any | ComputedOptions[js.Any]]]]
    var methods: js.UndefOr[js.Dictionary[js.Function]]
    var watch: js.UndefOr[js.Dictionary[WatchItem]]
    var el: js.UndefOr[Element | String]
    var template: js.UndefOr[String]
    var render: js.UndefOr[js.ThisFunction1[Vue, CreateElement, VNode]]
    var renderError: js.UndefOr[js.Function2[CreateElement, js.Error, VNode]]
    var staticRenderFns: js.UndefOr[js.Array[js.Function1[CreateElement, VNode]]]
    var beforeCreate: js.UndefOr[js.ThisFunction0[Vue, Unit]]
    var created: js.UndefOr[js.ThisFunction0[Vue, Unit]]
    var beforeDestroy: js.UndefOr[js.ThisFunction0[Vue, Unit]]
    var destroyed: js.UndefOr[js.ThisFunction0[Vue, Unit]]
    var beforeMount: js.UndefOr[js.ThisFunction0[Vue, Unit]]
    var mounted: js.UndefOr[js.ThisFunction0[Vue, Unit]]
    var beforeUpdate: js.UndefOr[js.ThisFunction0[Vue, Unit]]
    var updated: js.UndefOr[js.ThisFunction0[Vue, Unit]]
    var activated: js.UndefOr[js.ThisFunction0[Vue, Unit]]
    var deactivated: js.UndefOr[js.ThisFunction0[Vue, Unit]]
    var errorCaptured: js.UndefOr[js.Function3[js.Error, Vue, String, js.UndefOr[Boolean]]]
    var serverPrefetch: js.UndefOr[js.ThisFunction0[Vue, js.Promise[Unit]]]
    var directives: js.UndefOr[js.Dictionary[DirectiveFunction | DirectiveOptions]]
    var components: js.UndefOr[js.Dictionary[Component]]
    var transitions: js.UndefOr[js.Dictionary[js.Object]]
    var filters: js.UndefOr[js.Dictionary[js.Function]]
    var provide: js.UndefOr[js.Object | js.Function0[js.Object]]
    var inject: js.UndefOr[InjectOptions]
    var model: js.UndefOr[ComponentOptions.Model]
    var parent: js.UndefOr[Vue]
    var mixins: js.UndefOr[js.Array[ComponentOptions | VueConstructor]]
    var name: js.UndefOr[String]
    var `extends`: js.UndefOr[ComponentOptions | VueConstructor]
    var delimiters: js.UndefOr[js.Tuple2[String, String]]
    var comments: js.UndefOr[Boolean]
    var inheritAttrs: js.UndefOr[Boolean]
  }

  object ComponentOptions {
    def apply(
      data: js.UndefOr[js.ThisFunction0[Vue, js.Object] | js.Function0[js.Object]] = js.undefined,
      props: js.UndefOr[PropsDefinition] = js.undefined,
      propsData: js.UndefOr[js.Object | js.Dictionary[js.Any]] = js.undefined,
      computed: js.UndefOr[js.Dictionary[js.Function0[js.Any | ComputedOptions[js.Any]]]] = js.undefined,
      methods: js.UndefOr[js.Dictionary[js.Function]] = js.undefined,
      watch: js.UndefOr[js.Dictionary[WatchItem]] = js.undefined,
      el: js.UndefOr[Element | String] = js.undefined,
      template: js.UndefOr[String] = js.undefined,
      render: js.UndefOr[js.ThisFunction1[Vue, CreateElement, VNode]] = js.undefined,
      renderError: js.UndefOr[js.Function2[CreateElement, js.Error, VNode]] = js.undefined,
      staticRenderFns: js.UndefOr[js.Array[js.Function1[CreateElement, VNode]]] = js.undefined,
      beforeCreate: js.UndefOr[js.ThisFunction0[Vue, Unit]] = js.undefined,
      created: js.UndefOr[js.ThisFunction0[Vue, Unit]] = js.undefined,
      beforeDestroy: js.UndefOr[js.ThisFunction0[Vue, Unit]] = js.undefined,
      destroyed: js.UndefOr[js.ThisFunction0[Vue, Unit]] = js.undefined,
      beforeMount: js.UndefOr[js.ThisFunction0[Vue, Unit]] = js.undefined,
      mounted: js.UndefOr[js.ThisFunction0[Vue, Unit]] = js.undefined,
      beforeUpdate: js.UndefOr[js.ThisFunction0[Vue, Unit]] = js.undefined,
      updated: js.UndefOr[js.ThisFunction0[Vue, Unit]] = js.undefined,
      activated: js.UndefOr[js.ThisFunction0[Vue, Unit]] = js.undefined,
      deactivated: js.UndefOr[js.ThisFunction0[Vue, Unit]] = js.undefined,
      errorCaptured: js.UndefOr[js.Function3[js.Error, Vue, String, js.UndefOr[Boolean]]] = js.undefined,
      serverPrefetch: js.UndefOr[js.ThisFunction0[Vue, js.Promise[Unit]]] = js.undefined,
      directives: js.UndefOr[js.Dictionary[DirectiveFunction | DirectiveOptions]] = js.undefined,
      components: js.UndefOr[js.Dictionary[Component]] = js.undefined,
      transitions: js.UndefOr[js.Dictionary[js.Object]] = js.undefined,
      filters: js.UndefOr[js.Dictionary[js.Function]] = js.undefined,
      provide: js.UndefOr[js.Object | js.Function0[js.Object]] = js.undefined,
      inject: js.UndefOr[InjectOptions] = js.undefined,
      model: js.UndefOr[ComponentOptions.Model] = js.undefined,
      parent: js.UndefOr[Vue] = js.undefined,
      mixins: js.UndefOr[js.Array[ComponentOptions | VueConstructor]] = js.undefined,
      name: js.UndefOr[String] = js.undefined,
      `extends`: js.UndefOr[ComponentOptions | VueConstructor] = js.undefined,
      delimiters: js.UndefOr[js.Tuple2[String, String]] = js.undefined,
      comments: js.UndefOr[Boolean] = js.undefined,
      inheritAttrs: js.UndefOr[Boolean] = js.undefined
    ): ComponentOptions = {
      val _obj$ = js.Dynamic.literal(
      )
      data.foreach(_v => _obj$.updateDynamic("data")(_v.asInstanceOf[js.Any]))
      props.foreach(_v => _obj$.updateDynamic("props")(_v.asInstanceOf[js.Any]))
      propsData.foreach(_v => _obj$.updateDynamic("propsData")(_v.asInstanceOf[js.Any]))
      computed.foreach(_v => _obj$.updateDynamic("computed")(_v.asInstanceOf[js.Any]))
      methods.foreach(_v => _obj$.updateDynamic("methods")(_v.asInstanceOf[js.Any]))
      watch.foreach(_v => _obj$.updateDynamic("watch")(_v.asInstanceOf[js.Any]))
      el.foreach(_v => _obj$.updateDynamic("el")(_v.asInstanceOf[js.Any]))
      template.foreach(_v => _obj$.updateDynamic("template")(_v.asInstanceOf[js.Any]))
      render.foreach(_v => _obj$.updateDynamic("render")(_v.asInstanceOf[js.Any]))
      renderError.foreach(_v => _obj$.updateDynamic("renderError")(_v.asInstanceOf[js.Any]))
      staticRenderFns.foreach(_v => _obj$.updateDynamic("staticRenderFns")(_v.asInstanceOf[js.Any]))
      beforeCreate.foreach(_v => _obj$.updateDynamic("beforeCreate")(_v.asInstanceOf[js.Any]))
      created.foreach(_v => _obj$.updateDynamic("created")(_v.asInstanceOf[js.Any]))
      beforeDestroy.foreach(_v => _obj$.updateDynamic("beforeDestroy")(_v.asInstanceOf[js.Any]))
      destroyed.foreach(_v => _obj$.updateDynamic("destroyed")(_v.asInstanceOf[js.Any]))
      beforeMount.foreach(_v => _obj$.updateDynamic("beforeMount")(_v.asInstanceOf[js.Any]))
      mounted.foreach(_v => _obj$.updateDynamic("mounted")(_v.asInstanceOf[js.Any]))
      beforeUpdate.foreach(_v => _obj$.updateDynamic("beforeUpdate")(_v.asInstanceOf[js.Any]))
      updated.foreach(_v => _obj$.updateDynamic("updated")(_v.asInstanceOf[js.Any]))
      activated.foreach(_v => _obj$.updateDynamic("activated")(_v.asInstanceOf[js.Any]))
      deactivated.foreach(_v => _obj$.updateDynamic("deactivated")(_v.asInstanceOf[js.Any]))
      errorCaptured.foreach(_v => _obj$.updateDynamic("errorCaptured")(_v.asInstanceOf[js.Any]))
      serverPrefetch.foreach(_v => _obj$.updateDynamic("serverPrefetch")(_v.asInstanceOf[js.Any]))
      directives.foreach(_v => _obj$.updateDynamic("directives")(_v.asInstanceOf[js.Any]))
      components.foreach(_v => _obj$.updateDynamic("components")(_v.asInstanceOf[js.Any]))
      transitions.foreach(_v => _obj$.updateDynamic("transitions")(_v.asInstanceOf[js.Any]))
      filters.foreach(_v => _obj$.updateDynamic("filters")(_v.asInstanceOf[js.Any]))
      provide.foreach(_v => _obj$.updateDynamic("provide")(_v.asInstanceOf[js.Any]))
      inject.foreach(_v => _obj$.updateDynamic("inject")(_v.asInstanceOf[js.Any]))
      model.foreach(_v => _obj$.updateDynamic("model")(_v.asInstanceOf[js.Any]))
      parent.foreach(_v => _obj$.updateDynamic("parent")(_v.asInstanceOf[js.Any]))
      mixins.foreach(_v => _obj$.updateDynamic("mixins")(_v.asInstanceOf[js.Any]))
      name.foreach(_v => _obj$.updateDynamic("name")(_v.asInstanceOf[js.Any]))
      `extends`.foreach(_v => _obj$.updateDynamic("extends")(_v.asInstanceOf[js.Any]))
      delimiters.foreach(_v => _obj$.updateDynamic("delimiters")(_v.asInstanceOf[js.Any]))
      comments.foreach(_v => _obj$.updateDynamic("comments")(_v.asInstanceOf[js.Any]))
      inheritAttrs.foreach(_v => _obj$.updateDynamic("inheritAttrs")(_v.asInstanceOf[js.Any]))
      _obj$.asInstanceOf[ComponentOptions]
    }

    trait Model extends js.Object {
      var prop: js.UndefOr[String]
      var event: js.UndefOr[String]
    }

    object Model {
      def apply(
        prop: js.UndefOr[String] = js.undefined,
        event: js.UndefOr[String] = js.undefined
      ): Model = {
        val _obj$ = js.Dynamic.literal(
        )
        prop.foreach(_v => _obj$.updateDynamic("prop")(_v.asInstanceOf[js.Any]))
        event.foreach(_v => _obj$.updateDynamic("event")(_v.asInstanceOf[js.Any]))
        _obj$.asInstanceOf[Model]
      }
    }
  }

  trait FunctionalComponentOptions extends js.Object {
    var name: js.UndefOr[String]
    var props: js.UndefOr[PropsDefinition]
    var model: js.UndefOr[FunctionalComponentOptions.Model]
    var inject: js.UndefOr[InjectOptions]
    var functional: Boolean
    var render: js.UndefOr[js.Function2[CreateElement, RenderContext, VNode | js.Array[VNode]]]
  }

  object FunctionalComponentOptions {
    def apply(
      functional: true,
      name: js.UndefOr[String] = js.undefined,
      props: js.UndefOr[PropsDefinition] = js.undefined,
      model: js.UndefOr[FunctionalComponentOptions.Model] = js.undefined,
      inject: js.UndefOr[InjectOptions] = js.undefined,
      render: js.UndefOr[js.Function2[CreateElement, RenderContext, VNode | js.Array[VNode]]]
      //      render: js.UndefOr[(CreateElement, RenderContext) => VNode | js.Array[VNode]]
    ): FunctionalComponentOptions = {
      val _obj$ = js.Dynamic.literal(
        "functional" -> functional.asInstanceOf[js.Any]
      )
      name.foreach(_v => _obj$.updateDynamic("name")(_v.asInstanceOf[js.Any]))
      props.foreach(_v => _obj$.updateDynamic("props")(_v.asInstanceOf[js.Any]))
      model.foreach(_v => _obj$.updateDynamic("model")(_v.asInstanceOf[js.Any]))
      inject.foreach(_v => _obj$.updateDynamic("inject")(_v.asInstanceOf[js.Any]))
      render.foreach(_v => _obj$.updateDynamic("render")(_v.asInstanceOf[js.Any]))
      _obj$.asInstanceOf[FunctionalComponentOptions]
    }

    trait Model extends js.Object {
      var prop: js.UndefOr[String]
      var event: js.UndefOr[String]
    }

    object Model {
      def apply(
        prop: js.UndefOr[String] = js.undefined,
        event: js.UndefOr[String] = js.undefined
      ): Model = {
        val _obj$ = js.Dynamic.literal(
        )
        prop.foreach(_v => _obj$.updateDynamic("prop")(_v.asInstanceOf[js.Any]))
        event.foreach(_v => _obj$.updateDynamic("event")(_v.asInstanceOf[js.Any]))
        _obj$.asInstanceOf[Model]
      }
    }
  }

  trait RenderContext extends js.Object {
    var props: js.Dynamic
    var children: js.Array[VNode]
    def slots(): js.Dictionary[js.UndefOr[js.Array[VNode]]]
    var data: VNodeData
    var parent: Vue
    var listeners: js.Dictionary[js.Function | js.Array[js.Function]]
    var scopedSlots: js.Dictionary[NormalizedScopedSlot]
    var injections: js.Any
  }

  type PropType[T] = js.Function0[T]
  type PropsDefinition = js.Array[String] | js.Dictionary[PropOptions] | js.Dictionary[PropType[js.Any]]

  // PropOptions[T] にすると推論が面倒になりがち。。
  trait PropOptions extends js.Object {
    var `type`: js.UndefOr[PropType[js.Any]] = js.undefined
    var required: js.UndefOr[Boolean] = js.undefined
    var default: js.UndefOr[js.Any | js.Function0[js.UndefOr[js.Any]]] = js.undefined
    var validator: js.UndefOr[js.Function1[js.Any, Boolean]] = js.undefined
  }

  object PropOptions {
    // defaultに使うUはTで代用してもコンパイルは通るが、IntelliJが波線出しがち…
    // TODO: 非js型を無理矢理通すなら制約を緩める
    def apply[T](`type`: PropType[T])(implicit ev: T => js.Any): PropOptions = js.Dynamic.literal(`type` = `type`, required = true).asInstanceOf[PropOptions]
    def apply[T, U](`type`: PropType[T], default: U)(implicit ev: T => js.Any, ev2: U => T): PropOptions = {
      js.Dynamic.literal(`type` = `type`, default = default.asInstanceOf[js.Any]).asInstanceOf[PropOptions]
    }
    def apply[T, U](`type`: PropType[T], default: js.Function0[U])(implicit ev: T => js.Any, ev2: U => T): PropOptions = {
      js.Dynamic.literal(`type` = `type`, default = default).asInstanceOf[PropOptions]
    }
  }

  object PropType {
    def String: PropType[js.UndefOr[String]] = js.Dynamic.global.String.asInstanceOf[PropType[js.UndefOr[String]]]
    def Number: PropType[js.UndefOr[Double]] = js.Dynamic.global.Number.asInstanceOf[PropType[js.UndefOr[Double]]]
    def Boolean: PropType[js.UndefOr[Boolean]] = js.Dynamic.global.Boolean.asInstanceOf[PropType[js.UndefOr[Boolean]]]
    def Array[T]: PropType[js.UndefOr[js.Array[T]]] = js.Dynamic.global.Array.asInstanceOf[PropType[js.UndefOr[js.Array[T]]]]
    def Object[T]: PropType[js.UndefOr[T]] = js.Dynamic.global.Object.asInstanceOf[PropType[js.UndefOr[T]]]
    def Date: PropType[js.UndefOr[js.Date]] = js.Dynamic.global.Date.asInstanceOf[PropType[js.UndefOr[js.Date]]]
    // PropType.Function[Int => String] みたいに書こうと思うと、js.Functionでは絞れない。し、こっちの方がdefaultの型推論と相性がいい。
    def Function[T]: PropType[js.UndefOr[T]] = js.Dynamic.global.Function.asInstanceOf[PropType[js.UndefOr[T]]]
  }

  trait ComputedOptions[T] extends js.Object {
    def get(): T
    def set(value: T): Unit
    var cache: js.UndefOr[Boolean]
  }

  trait WatchOptions extends js.Object {
    var deep: js.UndefOr[Boolean]
    var immediate: js.UndefOr[Boolean]
  }

  object WatchOptions {
    def apply(
      deep: js.UndefOr[Boolean] = js.undefined,
      immediate: js.UndefOr[Boolean] = js.undefined
    ): WatchOptions = {
      val _obj$ = js.Dynamic.literal(
      )
      deep.foreach(_v => _obj$.updateDynamic("deep")(_v.asInstanceOf[js.Any]))
      immediate.foreach(_v => _obj$.updateDynamic("immediate")(_v.asInstanceOf[js.Any]))
      _obj$.asInstanceOf[WatchOptions]
    }
  }

  trait WatchOptionsWithHandler[T] extends WatchOptions {
    var handler: WatchHandler[T]
  }

  object WatchOptionsWithHandler {
    def apply[T](
      handler: WatchHandler[T],
      deep: js.UndefOr[Boolean] = js.undefined,
      immediate: js.UndefOr[Boolean] = js.undefined
    ): WatchOptionsWithHandler[T] = {
      val _obj$ = js.Dynamic.literal(
        "handler" -> handler.asInstanceOf[js.Any]
      )
      deep.foreach(_v => _obj$.updateDynamic("deep")(_v.asInstanceOf[js.Any]))
      immediate.foreach(_v => _obj$.updateDynamic("immediate")(_v.asInstanceOf[js.Any]))
      _obj$.asInstanceOf[WatchOptionsWithHandler[T]]
    }
  }

  trait DirectiveBinding extends VNodeDirective {
    //    override def modifiers: js.Dictionary[Boolean]
  }

  trait DirectiveOptions extends js.Object {
    var bind: js.UndefOr[DirectiveFunction]
    var inserted: js.UndefOr[DirectiveFunction]
    var update: js.UndefOr[DirectiveFunction]
    var componentUpdated: js.UndefOr[DirectiveFunction]
    var unbind: js.UndefOr[DirectiveFunction]
  }

  object DirectiveOptions {
    def apply(
      bind: js.UndefOr[DirectiveFunction] = js.undefined,
      inserted: js.UndefOr[DirectiveFunction] = js.undefined,
      update: js.UndefOr[DirectiveFunction] = js.undefined,
      componentUpdated: js.UndefOr[DirectiveFunction] = js.undefined,
      unbind: js.UndefOr[DirectiveFunction] = js.undefined
    ): DirectiveOptions = {
      val _obj$ = js.Dynamic.literal(
      )
      bind.foreach(_v => _obj$.updateDynamic("bind")(_v.asInstanceOf[js.Any]))
      inserted.foreach(_v => _obj$.updateDynamic("inserted")(_v.asInstanceOf[js.Any]))
      update.foreach(_v => _obj$.updateDynamic("update")(_v.asInstanceOf[js.Any]))
      componentUpdated.foreach(_v => _obj$.updateDynamic("componentUpdated")(_v.asInstanceOf[js.Any]))
      unbind.foreach(_v => _obj$.updateDynamic("unbind")(_v.asInstanceOf[js.Any]))
      _obj$.asInstanceOf[DirectiveOptions]
    }
  }
}