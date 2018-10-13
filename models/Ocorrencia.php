<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "ocorrencia".
 *
 * @property string $idocorrencia
 * @property string $nome_oco
 * @property string $capitulacao_oco
 * @property string $lei_oco
 * @property string $tempo_eco
 *
 * @property SosOcorrencia[] $sosOcorrencias
 */
class Ocorrencia extends \yii\db\ActiveRecord
{
    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'ocorrencia';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['tempo_eco'], 'safe'],
            [['nome_oco', 'capitulacao_oco', 'lei_oco'], 'string', 'max' => 45],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'idocorrencia' => Yii::t('app', 'Idocorrencia'),
            'nome_oco' => Yii::t('app', 'Nome Oco'),
            'capitulacao_oco' => Yii::t('app', 'Capitulacao Oco'),
            'lei_oco' => Yii::t('app', 'Lei Oco'),
            'tempo_eco' => Yii::t('app', 'Tempo Eco'),
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getSosOcorrencias()
    {
        return $this->hasMany(SosOcorrencia::className(), ['ocorrencia' => 'idocorrencia']);
    }
}
