<?php

use yii\helpers\Html;
use yii\widgets\DetailView;

/* @var $this yii\web\View */
/* @var $model app\models\Sos */

$this->title = $model->idsos;
$this->params['breadcrumbs'][] = ['label' => Yii::t('app', 'Sos'), 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="sos-view">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a(Yii::t('app', 'Update'), ['update', 'id' => $model->idsos], ['class' => 'btn btn-primary']) ?>
        <?= Html::a(Yii::t('app', 'Delete'), ['delete', 'id' => $model->idsos], [
            'class' => 'btn btn-danger',
            'data' => [
                'confirm' => Yii::t('app', 'Are you sure you want to delete this item?'),
                'method' => 'post',
            ],
        ]) ?>
    </p>

    <?= DetailView::widget([
        'model' => $model,
        'attributes' => [
            'idsos',
            'data_sos',
            'hora_sos',
            'usuario_sos',
            'latitude_sos',
            'longitude_sos',
            'atendido_sos',
            'vizualizado_sos',
            'descricao_sos:ntext',
        ],
    ]) ?>

</div>
