# Kotlinコーディング規約 v1.0
- [Kotlinコーディング規約](https://developer.android.com/kotlin/style-guide?hl=ja)を文字起こししたもの
- 上記のサイトを見ても対応できない場合は[Javaコーディング規約](https://google.github.io/styleguide/javaguide.html)を参照すること
- それでも解決できない場合は井上または中村に確認すること
- プロジェクト独自の規約には先頭に [original] がついている

## 構造

- 各ktファイルは以下のような構造とする。なお各セクションは空行で区切ること

1. パッケージステートメント
1. インポートステートメント
1. ファイルの説明(概要、更新日、更新者)
1. 処理

    ```
    package ~~~

    import ~~~
    import ~~~
    import ~~~

    /**
    * コーディング規約説明用クラス
    *
    * 2021/06/12 20:00 中村 クラスの作成
    * 2021/06/13 24:00 中村 リファクタリング
    */

    class Sample {
    }
    ```
- [original] クラスのプロパティやメソッドは公開範囲の広い順（アクセス修飾子順）に記述する。なお範囲が同じ場合はabc順とする

    ```
    class Sample {
        public val hoge = "hoge"
        protected val huga = "huga"
        private val piyo = "piyo"

        public fun setHoge() {}
        protected fun setHuga() {}
        private fun setPiyo() {}
    }
    ```

<br />

## 共通
- インデントはスペース4つとする
- 1行100文字を超えるような場合には改行を行うこと
- 変数宣言は可能な限り、再代入が不可能なvalを使用する

<br />

## コメント
- [original] 複数行のコメント形式である/* */を使用すること
- 段落が移る場合は改行を挟むこと
- [original] なるべくコメントを書く必要のないコードを意識すること

<br />

## 中かっこ
- 中かっこは以下のようなKernighan&Ritchieスタイルに従う

    ```
    fun hoge() {
        if (true) {
            try {
                ~~~
            } catch (e: Exception) {
                ~~~
            }
        } else {
            ~~~
        }
    }
    ```

<br />

## 命名

### パッケージ名
- パッケージ名は連続した単語をアンダースコアなしで繋げ、すべて小文字とする
    ```
    package com.example.hogehugapiyo
    ```

### クラス名
- クラス名・インターフェース名はPascalCaseで記述し、名詞または名詞句を使用する
- ただし場合によっては形容詞または形容詞句となる場合がある
- テストクラスの名前は対象のクラス名＋Testとなる。例えばHogeTest

### 関数名
- 関数名はcamelCaseで記述し、通常は動詞または動詞句を使用する

### 変数名
- 変数名はcamelCaseで記述し、通常は名詞または名詞句を使用すること

<br />

## ドキュメント
- アクセス修飾子がpublicとprotectedのメソッドにはKDocを記述すること
- [original] セッターとゲッターメソッドには不要とする

    ```
    /**
    * セッターなので不要
    */
    public fun setHoge() {
    }

    /**
    * ゲッターなので不要
    */
    public fun getHoge() {
    }

    /**
    * 引数にhogeを加えた文字を返す
    *
    * @args str 加えられる対象の文字
    * @return String 引数strと"hoge"を連結させた文字列
    */
    public fun createHoge(str: String): String {
    }
    ```