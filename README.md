# ScalaJS x Vue 2

ScalaJSとVue2でそこそこの書き味を探ったなにか。

単純なFacadeと言うには、以下のように魔改造している。

* 型推論がしんどいところの自由度を下げたり
* `PropOptions` `PropTypes` なんて記法を生み出したり
    ```
    props = js.Dictionary(
    "strProp" -> vue.PropOptions(vue.PropType.String),
    "strProp2" -> vue.PropOptions(vue.PropType.String, default = "default value"),
    "boolProp" -> vue.PropOptions(vue.PropType.Boolean, default = false),
    "boolProp2" -> vue.PropOptions(vue.PropType.Boolean, default = false)
    ),
    ```
* カスタムしたapplyが紛れこんでいたり

多分Scala3だともっと型推論の制約は減るんだろうなあのお気持ち。
