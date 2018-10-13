<?php

use yii\helpers\Html;
use yii\grid\GridView;

use dosamigos\google\maps\LatLng;
use dosamigos\google\maps\services\DirectionsWayPoint;
use dosamigos\google\maps\services\TravelMode;
use dosamigos\google\maps\overlays\PolylineOptions;
use dosamigos\google\maps\services\DirectionsRenderer;
use dosamigos\google\maps\services\DirectionsService;
use dosamigos\google\maps\overlays\InfoWindow;
use dosamigos\google\maps\overlays\Marker;
use dosamigos\google\maps\Map;
use dosamigos\google\maps\services\DirectionsRequest;
use dosamigos\google\maps\overlays\Polygon;
use dosamigos\google\maps\layers\BicyclingLayer;

/* @var $this yii\web\View */
/* @var $searchModel app\models\SosSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = Yii::t('app', 'Sos');
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="sos-index">

    <h1><?= Html::encode($this->title) ?></h1>
    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <p>
        <?php
        
        $marker = [];
        
        foreach ($dados as $valor) {
            $coord = new LatLng(['lat' => $valor->latitude_sos, 'lng' => $valor->longitude_sos]);
            $marker[$valor->idsos-1] = new Marker([
                'position' => $coord,
                'title' => $valor->hora_sos,
            ]);
        }        
        
        echo '<h3>Mapa de Ocorrências</h3>';
        $coord = new LatLng(['lat' => -6.260269, 'lng' => -36.519479]);
        $map = new Map([
            'center' => $coord,
            'zoom' => 16,
        ]);
        // Display the map -finally :)
        for ($i = 0; $i < count($marker); $i++) {
            $map->addOverlay($marker[$i]);
        }
        $map->width = 1000;
        echo $map->display();
    ?>
    </p>
    
    <p>
        <?= Html::a(Yii::t('app', 'Create Sos'), ['create'], ['class' => 'btn btn-success']) ?>
    </p>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'idsos',
            'data_sos',
            'hora_sos',
            'usuario_sos',
            'latitude_sos',
            //'longitude_sos',
            //'atendido_sos',
            //'vizualizado_sos',
            //'descricao_sos:ntext',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>
</div>
