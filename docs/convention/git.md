# git v1.0
gitに関する規約

## Milestones
- いわゆる1スプリントに相当する
- 継続的に行うものを除き、期限を設定する

<br />

## Issues
- マイルストーンをタスク単位に分割する
- 適切なタグを設定する

<br />

## branch
- コラボレーターはブランチの運用法として[Git flow](https://nvie.com/posts/a-successful-git-branching-model/)を採用
- master,develop,release,hotfix以外のブランチはプルリクエストmergeのタイミングで削除する
- コントリビューターはmasterブランチから分岐をしてdevelopブランチを作成する

### Git flow
- master: 正式リリース
- develop: 開発用ブランチ。リリース前最新ブランチ
- release: 正式リリース前の微調整。developから分岐し、masterとdevelopにmergeする
- hotfix: masterブランチで緊急修正を行う
- <ユーザ名>_<作業内容>: 機能追加やバグ修正を行う。developブランチから分岐し、developブランチにmergeする
    - 例: nakamura_bugfix: nakamuraがバグの修正を行う
    - 例: nakamura_feature: nakamuraが機能の追加を行う

<br />

## Commit/push
- キリの良いタイミングで行うこと
- コミットメッセージは<作業内容> #<issue番号>でissueとの関連付けを行う
    - issue例:#1 規約の作成
    - コミットメッセージ例: git規約の作成v0.1 #1

<br />

## Pull Requests
- develop,masterブランチへのmergeはレビュー必須とする

<br />

## Releases
- タグは[セマンティックバージョニング](https://semver.org/lang/ja/)を採用
- リリースタイトルは基本的にタグと一致させる
- beta版と開発版はpre-releaseとする