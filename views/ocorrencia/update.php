<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model app\models\Ocorrencia */

$this->title = Yii::t('app', 'Update Ocorrencia: ' . $model->idocorrencia, [
    'nameAttribute' => '' . $model->idocorrencia,
]);
$this->params['breadcrumbs'][] = ['label' => Yii::t('app', 'Ocorrencias'), 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->idocorrencia, 'url' => ['view', 'id' => $model->idocorrencia]];
$this->params['breadcrumbs'][] = Yii::t('app', 'Update');
?>
<div class="ocorrencia-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
