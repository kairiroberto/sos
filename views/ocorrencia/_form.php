<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $model app\models\Ocorrencia */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="ocorrencia-form">

    <?php $form = ActiveForm::begin(); ?>

    <?= $form->field($model, 'nome_oco')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'capitulacao_oco')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'lei_oco')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'tempo_eco')->textInput() ?>

    <div class="form-group">
        <?= Html::submitButton(Yii::t('app', 'Save'), ['class' => 'btn btn-success']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
