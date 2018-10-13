<?php

namespace app\models;

use Yii;
use yii\base\Model;
use yii\data\ActiveDataProvider;
use app\models\Ocorrencia;

/**
 * OcorrenciaSearch represents the model behind the search form of `app\models\Ocorrencia`.
 */
class OcorrenciaSearch extends Ocorrencia
{
    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['idocorrencia'], 'integer'],
            [['nome_oco', 'capitulacao_oco', 'lei_oco', 'tempo_eco'], 'safe'],
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
        $query = Ocorrencia::find();

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
            'idocorrencia' => $this->idocorrencia,
            'tempo_eco' => $this->tempo_eco,
        ]);

        $query->andFilterWhere(['like', 'nome_oco', $this->nome_oco])
            ->andFilterWhere(['like', 'capitulacao_oco', $this->capitulacao_oco])
            ->andFilterWhere(['like', 'lei_oco', $this->lei_oco]);

        return $dataProvider;
    }
}
