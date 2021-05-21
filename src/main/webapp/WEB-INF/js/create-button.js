/**
 * Build button 'create new cat'
 */
function buildNewCatButton() {
    Ext.create('Ext.Button', {
        text: 'Create',
        renderTo: 'buttons',
        handler: showCreateNewCatDialog
    });
}

function showCreateNewCatDialog() {
    Ext.define('CreateNewCat', {
        extend: 'Ext.window.Window',

        requires: [
            'Ext.form.Panel',
            'Ext.form.field.Display',
            'Ext.form.field.Text'
        ],

        title: 'Create new Cat',
        closable: true,
        autoShow: true,

        items: {
            xtype: 'form',
            bodyPadding: 10,
            reference: 'form',
            items: [{
                xtype: 'textfield',
                id: 'createNewCatName',
                name: 'name',
                fieldLabel: 'Name',
                allowBlank: false,
                validator: nameValidator
            }, {
                xtype: 'textfield',
                id: 'createNewCatAge',
                name: 'age',
                fieldLabel: 'Age',
                allowBlank: false,
                validator: ageValidator
            }],
            buttons: [{
                text: 'Create',
                formBind: true,
                handler: createNewCat
            }, {
                text: 'Cancel',
                handler: function (button) {
                    this.up('window').close();
                }
            }]
        }
    });

    new CreateNewCat().show();
}

/**
 * Send request for create cat with information
 */
function createNewCat() {
    const name = Ext.getCmp('createNewCatName').getValue();
    const age = Ext.getCmp('createNewCatAge').getValue();

    Ext.Ajax.request({
        url: '/api/cat/create',
        method: 'POST',
        jsonData: {
            name: name,
            age: age
        },
        success: function (response, opts) {
            let cats = JSON.parse(response.responseText);

            Ext.getCmp('catGrid').getStore().insert(0, {
                id: cats.id,
                name: cats.name,
                age: cats.age
            })
        },
        failure: function (response, opts) {
            Ext.Msg.alert('Error', 'Error in creating cat!');
        }
    });

    this.up('window').close();
}