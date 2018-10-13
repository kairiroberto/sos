<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $model app\models\SosOcorrenciaSearch */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="sos-ocorrencia-search">

    <?php $form = ActiveForm::begin([
        'action' => ['index'],
        'method' => 'get',
    ]); ?>

    <?= $form->field($model, 'idsos_ocorrencia') ?>

    <?= $form->field($model, 'sos') ?>

    <?= $form->field($model, 'ocorrencia') ?>

    <?= $form->field($model, 'termino') ?>

    <div class="form-group">
        <?= Html::submitButton(Yii::t('app', 'Search'), ['class' => 'btn btn-primary']) ?>
        <?= Html::resetButton(Yii::t('app', 'Reset'), ['class' => 'btn btn-default']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
