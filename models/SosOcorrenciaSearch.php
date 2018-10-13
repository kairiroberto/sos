<?php

namespace app\models;

use Yii;
use yii\base\Model;
use yii\data\ActiveDataProvider;
use app\models\SosOcorrencia;

/**
 * SosOcorrenciaSearch represents the model behind the search form of `app\models\SosOcorrencia`.
 */
class SosOcorrenciaSearch extends SosOcorrencia
{
    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['idsos_ocorrencia', 'sos', 'ocorrencia'], 'integer'],
            [['termino'], 'safe'],
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
        $query = SosOcorrencia::find();

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
            'idsos_ocorrencia' => $this->idsos_ocorrencia,
            'sos' => $this->sos,
            'ocorrencia' => $this->ocorrencia,
            'termino' => $this->termino,
        ]);

        return $dataProvider;
    }
}
