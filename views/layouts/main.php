<?php

/* @var $this \yii\web\View */
/* @var $content string */

use app\widgets\Alert;
use yii\helpers\Html;
use yii\bootstrap\Nav;
use yii\bootstrap\NavBar;
use yii\widgets\Breadcrumbs;
use app\assets\AppAsset;

AppAsset::register($this);
?>
<?php $this->beginPage() ?>
<!DOCTYPE html>
<html lang="<?= Yii::$app->language ?>">
<head>
    <meta charset="<?= Yii::$app->charset ?>">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <?= Html::csrfMetaTags() ?>
    <title><?= Html::encode($this->title) ?></title>
    <?php $this->head() ?>
</head>
<body>
<?php $this->beginBody() ?>

<div class="wrap">
    <?php
    NavBar::begin([
        'brandLabel' => Yii::$app->name,
        'brandUrl' => Yii::$app->homeUrl,
        'options' => [
            'class' => 'navbar-inverse navbar-fixed-top',
        ],
    ]);
    echo Nav::widget([
        'options' => ['class' => 'navbar-nav navbar-right'],
        'items' => [
            //['label' => 'Home', 'url' => ['/site/index']],
            //['label' => 'About', 'url' => ['/site/about']],
            //['label' => 'Contact', 'url' => ['/site/contact']],
            ['label' => 'Usuário', 
                'items' => [ 
                    ['label' => 'Usuário', 'url' => ['index.php/usuario/index']],
                    ['label' => 'Usuario - XML', 'url' => ['index.php/apiusuario/default']],
                ],
            ],
            ['label' => 'S.O.S.', 
                'items' => [
                    ['label' => 'S.O.S.', 'url' => ['index.php/sos/index']],
                    ['label' => 'S.O.S. - XML', 'url' => ['index.php/apisos/default']],
                ],
            ],
            ['label' => 'Ocorrências', 
                'items' => [
                    ['label' => 'Ocorrências', 'url' => ['index.php/ocorrencia/index']],
                    ['label' => 'Ocorrência - XML', 'url' => ['index.php/api/default']],
                ],
            ],       
            ['label' => 'S.O.S. - Ocorrência', 
                'items' => [
                    ['label' => 'S.O.S. - Ocorrência', 'url' => ['index.php/sos-ocorrencia/index']],
                    ['label' => 'S.O.S. - Ocorrência - XML', 'url' => ['index.php/api/default']],
                ],
            ],
            Yii::$app->user->isGuest ? (
                ['label' => 'Login', 'url' => ['/site/login']]
            ) : (
                '<li>'
                . Html::beginForm(['/site/logout'], 'post')
                . Html::submitButton(
                    'Logout (' . Yii::$app->user->identity->username . ')',
                    ['class' => 'btn btn-link logout']
                )
                . Html::endForm()
                . '</li>'
            )
        ],
    ]);
    NavBar::end();
    ?>

    <div class="container">
        <?= Breadcrumbs::widget([
            'links' => isset($this->params['breadcrumbs']) ? $this->params['breadcrumbs'] : [],
        ]) ?>
        <?= Alert::widget() ?>
        <?= $content ?>
    </div>
</div>

<footer class="footer">
    <div class="container">
        <p class="pull-left">&copy; My Company S.O.S.<?= date('Y') ?></p>

        <p class="pull-right"><?= Yii::powered() ?></p>
    </div>
</footer>

<?php $this->endBody() ?>
</body>
</html>
<?php $this->endPage() ?>
