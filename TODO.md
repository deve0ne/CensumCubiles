* Сейчас есть большая путаница с amount материала на складе и amount материала в модели.
Они должны быть независимыми от друг друга, для начала нужно по разному назвать в БД.
* Что вообще такое ModelTreeItem? Это злой старший брат Model? Надо выпилить его, по-моему он не нужен.
* При обновлении цены в Materials table цена в models table не обновляется. Думаю, нужно сделать какой-нибудь глобалный метод update, который будет обновлять все другие вкладки.