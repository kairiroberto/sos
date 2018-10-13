<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $model app\models\SosOcorrencia */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="sos-ocorrencia-form">

    <?php $form = ActiveForm::begin(); ?>

    <?= $form->field($model, 'sos')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'ocorrencia')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'termino')->textInput() ?>

    <div class="form-group">
        <?= Html::submitButton(Yii::t('app', 'Save'), ['class' => 'btn btn-success']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
