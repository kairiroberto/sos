<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Sos */

$this->title = Yii::t('app', 'Create Sos');
$this->params['breadcrumbs'][] = ['label' => Yii::t('app', 'Sos'), 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="sos-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
