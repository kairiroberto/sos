<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $model app\models\Usuario */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="usuario-form">

    <?php $form = ActiveForm::begin(); ?>

    <?= $form->field($model, 'nome_usu')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'celular_usu')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'fake_new_usu')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'ativo')->textInput() ?>

    <div class="form-group">
        <?= Html::submitButton(Yii::t('app', 'Save'), ['class' => 'btn btn-success']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
