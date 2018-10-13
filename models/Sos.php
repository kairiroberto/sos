<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "sos".
 *
 * @property string $idsos
 * @property string $data_sos
 * @property string $hora_sos
 * @property string $usuario_sos
 * @property double $latitude_sos
 * @property double $longitude_sos
 * @property int $atendido_sos
 * @property string $vizualizado_sos
 * @property string $descricao_sos
 *
 * @property Usuario $usuarioSos
 * @property SosOcorrencia[] $sosOcorrencias
 */
class Sos extends \yii\db\ActiveRecord
{
    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'sos';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['data_sos', 'hora_sos'], 'safe'],
            [['usuario_sos', 'atendido_sos', 'vizualizado_sos'], 'integer'],
            [['latitude_sos', 'longitude_sos'], 'number'],
            [['descricao_sos'], 'string'],
            [['usuario_sos'], 'exist', 'skipOnError' => true, 'targetClass' => Usuario::className(), 'targetAttribute' => ['usuario_sos' => 'idusuario']],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'idsos' => Yii::t('app', 'Idsos'),
            'data_sos' => Yii::t('app', 'Data Sos'),
            'hora_sos' => Yii::t('app', 'Hora Sos'),
            'usuario_sos' => Yii::t('app', 'Usuario Sos'),
            'latitude_sos' => Yii::t('app', 'Latitude Sos'),
            'longitude_sos' => Yii::t('app', 'Longitude Sos'),
            'atendido_sos' => Yii::t('app', 'Atendido Sos'),
            'vizualizado_sos' => Yii::t('app', 'Vizualizado Sos'),
            'descricao_sos' => Yii::t('app', 'Descricao Sos'),
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getUsuarioSos()
    {
        return $this->hasOne(Usuario::className(), ['idusuario' => 'usuario_sos']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getSosOcorrencias()
    {
        return $this->hasMany(SosOcorrencia::className(), ['sos' => 'idsos']);
    }
}
