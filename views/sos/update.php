<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model app\models\Sos */

$this->title = Yii::t('app', 'Update Sos: ' . $model->idsos, [
    'nameAttribute' => '' . $model->idsos,
]);
$this->params['breadcrumbs'][] = ['label' => Yii::t('app', 'Sos'), 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->idsos, 'url' => ['view', 'id' => $model->idsos]];
$this->params['breadcrumbs'][] = Yii::t('app', 'Update');
?>
<div class="sos-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
