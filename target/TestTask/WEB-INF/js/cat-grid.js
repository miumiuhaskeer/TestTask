/**
 * Get information about cats in database and create grid (table)
 */
function createCatGrid() {
    Ext.Ajax.request({
        url: '/api/cat',
        method: 'GET',
        success: function (response, opts) {
            let cats = JSON.parse(response.responseText);
            createGrid(cats);
        },
        failure: function (response, opts) {
            Ext.Msg.alert('Error', 'Error in load all cats!');
        }
    });
}

/**
 * Create grid (table) by info about cats (id, name, age)
 *
 * @param cats array of cat object
 */
function createGrid(cats) {
    let store = getStore(cats);

    Ext.create('Ext.grid.Panel', {
        id: 'catGrid',
        renderTo: 'grid',
        store: store,
        width: 455,
        height: 300,
        title: 'Cats table',
        columns: [
            {
                text: 'ID',
                sortable: false,
                dataIndex: 'id',
                width: 150
            },
            {
                text: 'Name',
                dataIndex: 'name',
                width: 150
            },
            {
                text: 'Age',
                dataIndex: 'age',
                width: 150
            }
        ]
    });
}

/**
 * Get store from cats
 *
 * @param cats array of cat object
 * @returns {Ext.data.SimpleStore} store for grid
 */
function getStore(cats) {
    let storeArray = [];
    let store = new Ext.data.SimpleStore();

    for (let i = 0; i < cats.length; i++) {
        storeArray.push({
            id: cats[i].id,
            name: cats[i].name,
            age: cats[i].age
        })
    }

    store.loadData(storeArray);

    return store;
}