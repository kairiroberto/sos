<?php

use yii\helpers\Html;
use yii\widgets\DetailView;

/* @var $this yii\web\View */
/* @var $model app\models\Ocorrencia */

$this->title = $model->idocorrencia;
$this->params['breadcrumbs'][] = ['label' => Yii::t('app', 'Ocorrencias'), 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="ocorrencia-view">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a(Yii::t('app', 'Update'), ['update', 'id' => $model->idocorrencia], ['class' => 'btn btn-primary']) ?>
        <?= Html::a(Yii::t('app', 'Delete'), ['delete', 'id' => $model->idocorrencia], [
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
            'idocorrencia',
            'nome_oco',
            'capitulacao_oco',
            'lei_oco',
            'tempo_eco',
        ],
    ]) ?>

</div>
