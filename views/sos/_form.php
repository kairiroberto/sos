<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $model app\models\Sos */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="sos-form">

    <?php $form = ActiveForm::begin(); ?>

    <?= $form->field($model, 'data_sos')->textInput() ?>

    <?= $form->field($model, 'hora_sos')->textInput() ?>

    <?= $form->field($model, 'usuario_sos')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'latitude_sos')->textInput() ?>

    <?= $form->field($model, 'longitude_sos')->textInput() ?>

    <?= $form->field($model, 'atendido_sos')->textInput() ?>

    <?= $form->field($model, 'vizualizado_sos')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'descricao_sos')->textarea(['rows' => 6]) ?>

    <div class="form-group">
        <?= Html::submitButton(Yii::t('app', 'Save'), ['class' => 'btn btn-success']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
