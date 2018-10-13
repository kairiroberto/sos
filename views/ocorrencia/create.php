<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Ocorrencia */

$this->title = Yii::t('app', 'Create Ocorrencia');
$this->params['breadcrumbs'][] = ['label' => Yii::t('app', 'Ocorrencias'), 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="ocorrencia-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
