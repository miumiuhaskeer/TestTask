/**
 * Build button 'edit cat'
 */
function buildEditCatButton() {
    Ext.create('Ext.Button', {
        text: 'Edit',
        renderTo: 'buttons',
        handler: showEditCatDialog
    });
}

function showEditCatDialog() {
    let sel = Ext.getCmp('catGrid').getSelectionModel().getSelection();
    let oldCat = sel[0];

    if (oldCat == null) {
        Ext.Msg.alert('Error', 'Please select the cat to be edit first!');
        return;
    }

    oldCat = oldCat.data;

    Ext.define('EditCat', {
        extend: 'Ext.window.Window',

        requires: [
            'Ext.form.Panel',
            'Ext.form.field.Display',
            'Ext.form.field.Text'
        ],

        title: 'Edit cat',
        closable: true,
        autoShow: true,

        items: {
            xtype: 'form',
            bodyPadding: 10,
            reference: 'form',
            items: [{
                xtype: 'textfield',
                id: 'editCatName',
                name: 'name',
                fieldLabel: 'Name',
                allowBlank: false,
                value: oldCat.name,
                validator: nameValidator
            }, {
                xtype: 'textfield',
                id: 'editCatAge',
                name: 'age',
                fieldLabel: 'Age',
                allowBlank: false,
                value: oldCat.age,
                validator: ageValidator
            }],
            buttons: [{
                text: 'Edit',
                formBind: true,
                handler: function (button) {
                    editCat(oldCat, this.up('window'));
                }
            }, {
                text: 'Cancel',
                handler: function (button) {
                    this.up('window').close();
                }
            }]
        }
    });

    new EditCat().show();
}

/**
 * Send request for edit cat information
 *
 * @param oldCat object that contain old information about cat
 * @param window current dialog window
 */
function editCat(oldCat, window) {
    const id = oldCat.id;
    const name = Ext.getCmp('editCatName').getValue();
    const age = Ext.getCmp('editCatAge').getValue();

    Ext.Ajax.request({
        url: '/api/cat/edit/' + id,
        method: 'PATCH',
        jsonData: {
            name: name,
            age: age
        },
        success: function (response, opts) {
            oldCat.name = name;
            oldCat.age = age;

            Ext.getCmp('catGrid').getView().refresh();
        },
        failure: function (response, opts) {
            Ext.Msg.alert('Error', 'Error in editing cat!');
        }
    });

    window.close();
}