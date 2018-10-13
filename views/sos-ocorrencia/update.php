<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model app\models\SosOcorrencia */

$this->title = Yii::t('app', 'Update Sos Ocorrencia: ' . $model->idsos_ocorrencia, [
    'nameAttribute' => '' . $model->idsos_ocorrencia,
]);
$this->params['breadcrumbs'][] = ['label' => Yii::t('app', 'Sos Ocorrencias'), 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->idsos_ocorrencia, 'url' => ['view', 'id' => $model->idsos_ocorrencia]];
$this->params['breadcrumbs'][] = Yii::t('app', 'Update');
?>
<div class="sos-ocorrencia-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
