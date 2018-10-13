<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $model app\models\OcorrenciaSearch */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="ocorrencia-search">

    <?php $form = ActiveForm::begin([
        'action' => ['index'],
        'method' => 'get',
    ]); ?>

    <?= $form->field($model, 'idocorrencia') ?>

    <?= $form->field($model, 'nome_oco') ?>

    <?= $form->field($model, 'capitulacao_oco') ?>

    <?= $form->field($model, 'lei_oco') ?>

    <?= $form->field($model, 'tempo_eco') ?>

    <div class="form-group">
        <?= Html::submitButton(Yii::t('app', 'Search'), ['class' => 'btn btn-primary']) ?>
        <?= Html::resetButton(Yii::t('app', 'Reset'), ['class' => 'btn btn-default']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
