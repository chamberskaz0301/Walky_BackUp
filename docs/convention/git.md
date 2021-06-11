# git

## スクラムと git

### Milestones

- スプリントバックログに利用
- 必ず期限を設定すること

### issues

- スプリントのタスク管理に利用

<br>

## 開発の流れ

### 1 度だけする作業

1. 代表者のリポジトリを fork する
1. <自分の名前>/<リポジトリ名>になっていることを確認したら、development ブランチをローカルに clone する
1. upstream という名前で代表者のリポジトリを登録する

### 開発の度に繰り返す作業

1. issue と Milestone を確認する
1. development ブランチから、dev\_<[作業内容](#tag)>ブランチを作成する
1. キリの良いタイミングで add と commit を行う。<コメント> <#issue 番号>で issue と関連付けを行う
   - issue 例: 規約の作成#1
   - commit コメント例: コーディング規約の作成 #1

### 開発が完了したら

1. development ブランチに移動して、dev\_<[作業内容](#tag)>を merge する
1. dev\_<[作業内容](#tag)>ブランチを削除する
1. development ブランチを push する
1. development -> master に <作業した内容> -> master というコメントで pull request する
1. <自分の名前>/master -> <代表者の名前>/development に pull requests する
1. 代表者のリポジトリに変更があれば upstream から pull する

### ただしコラボレーターの場合は

1. development ブランチから dev\_<名前>ブランチを作成する
1. dev*<名前>ブランチから更に dev*<[作業内容](#tag)>ブランチを作成する
1. 開発が完了したら dev\_<名前>ブランチに移動して merge->push

<br>

## ブランチ名

- 代表者/master: 本番リリース
- 代表者/development: 開発用
- 代表者/dev\_<名前>: コラボレーター開発用ブランチ
- 代表者/dev\_<[作業内容](#tag)>: 作業用

- コントリビューター/master: pull request 用
- コントリビューター/development: 開発用
- コントリビューター/dev\_<[作業内容](#tag)>: 作業用

### <a id="tag">作業内容一覧</a>

以下を参考に英語で記載すること

- bug: バグ修正
- doc: 設計などドキュメント
- feature: 新機能
