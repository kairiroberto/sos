<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $model app\models\SosSearch */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="sos-search">

    <?php $form = ActiveForm::begin([
        'action' => ['index'],
        'method' => 'get',
    ]); ?>

    <?= $form->field($model, 'idsos') ?>

    <?= $form->field($model, 'data_sos') ?>

    <?= $form->field($model, 'hora_sos') ?>

    <?= $form->field($model, 'usuario_sos') ?>

    <?= $form->field($model, 'latitude_sos') ?>

    <?php // echo $form->field($model, 'longitude_sos') ?>

    <?php // echo $form->field($model, 'atendido_sos') ?>

    <?php // echo $form->field($model, 'vizualizado_sos') ?>

    <?php // echo $form->field($model, 'descricao_sos') ?>

    <div class="form-group">
        <?= Html::submitButton(Yii::t('app', 'Search'), ['class' => 'btn btn-primary']) ?>
        <?= Html::resetButton(Yii::t('app', 'Reset'), ['class' => 'btn btn-default']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
