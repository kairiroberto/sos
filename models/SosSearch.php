<?php

namespace app\models;

use Yii;
use yii\base\Model;
use yii\data\ActiveDataProvider;
use app\models\Sos;

/**
 * SosSearch represents the model behind the search form of `app\models\Sos`.
 */
class SosSearch extends Sos
{
    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['idsos', 'usuario_sos', 'atendido_sos', 'vizualizado_sos'], 'integer'],
            [['data_sos', 'hora_sos', 'descricao_sos'], 'safe'],
            [['latitude_sos', 'longitude_sos'], 'number'],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function scenarios()
    {
        // bypass scenarios() implementation in the parent class
        return Model::scenarios();
    }

    /**
     * Creates data provider instance with search query applied
     *
     * @param array $params
     *
     * @return ActiveDataProvider
     */
    public function search($params)
    {
        $query = Sos::find();

        // add conditions that should always apply here

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
        ]);

        $this->load($params);

        if (!$this->validate()) {
            // uncomment the following line if you do not want to return any records when validation fails
            // $query->where('0=1');
            return $dataProvider;
        }

        // grid filtering conditions
        $query->andFilterWhere([
            'idsos' => $this->idsos,
            'data_sos' => $this->data_sos,
            'hora_sos' => $this->hora_sos,
            'usuario_sos' => $this->usuario_sos,
            'latitude_sos' => $this->latitude_sos,
            'longitude_sos' => $this->longitude_sos,
            'atendido_sos' => $this->atendido_sos,
            'vizualizado_sos' => $this->vizualizado_sos,
        ]);

        $query->andFilterWhere(['like', 'descricao_sos', $this->descricao_sos]);

        return $dataProvider;
    }
}
