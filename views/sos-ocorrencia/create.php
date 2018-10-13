<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\SosOcorrencia */

$this->title = Yii::t('app', 'Create Sos Ocorrencia');
$this->params['breadcrumbs'][] = ['label' => Yii::t('app', 'Sos Ocorrencias'), 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="sos-ocorrencia-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
