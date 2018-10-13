<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "sos_ocorrencia".
 *
 * @property string $idsos_ocorrencia
 * @property string $sos
 * @property string $ocorrencia
 * @property string $termino
 *
 * @property Ocorrencia $ocorrencia0
 * @property Sos $sos0
 */
class SosOcorrencia extends \yii\db\ActiveRecord
{
    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'sos_ocorrencia';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['sos', 'ocorrencia'], 'integer'],
            [['termino'], 'safe'],
            [['ocorrencia'], 'exist', 'skipOnError' => true, 'targetClass' => Ocorrencia::className(), 'targetAttribute' => ['ocorrencia' => 'idocorrencia']],
            [['sos'], 'exist', 'skipOnError' => true, 'targetClass' => Sos::className(), 'targetAttribute' => ['sos' => 'idsos']],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'idsos_ocorrencia' => Yii::t('app', 'Idsos Ocorrencia'),
            'sos' => Yii::t('app', 'Sos'),
            'ocorrencia' => Yii::t('app', 'Ocorrencia'),
            'termino' => Yii::t('app', 'Termino'),
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getOcorrencia0()
    {
        return $this->hasOne(Ocorrencia::className(), ['idocorrencia' => 'ocorrencia']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getSos0()
    {
        return $this->hasOne(Sos::className(), ['idsos' => 'sos']);
    }
}
