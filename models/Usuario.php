<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "usuario".
 *
 * @property string $idusuario
 * @property string $nome_usu
 * @property string $celular_usu
 * @property string $fake_new_usu
 * @property int $ativo
 *
 * @property Sos[] $sos
 */
class Usuario extends \yii\db\ActiveRecord
{
    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'usuario';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['fake_new_usu', 'ativo'], 'integer'],
            [['nome_usu'], 'string', 'max' => 100],
            [['celular_usu'], 'string', 'max' => 45],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'idusuario' => Yii::t('app', 'Idusuario'),
            'nome_usu' => Yii::t('app', 'Nome Usu'),
            'celular_usu' => Yii::t('app', 'Celular Usu'),
            'fake_new_usu' => Yii::t('app', 'Fake New Usu'),
            'ativo' => Yii::t('app', 'Ativo'),
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getSos()
    {
        return $this->hasMany(Sos::className(), ['usuario_sos' => 'idusuario']);
    }
}
