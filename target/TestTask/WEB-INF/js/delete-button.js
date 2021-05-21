/**
 * Build button 'delete cat'
 */
function buildDeleteCatButton() {
    Ext.create('Ext.Button', {
        text: 'Delete',
        renderTo: 'buttons',
        handler: showDeleteCatDialog
    });
}

function showDeleteCatDialog() {
    let sel = Ext.getCmp('catGrid').getSelectionModel().getSelection();
    let delCat = sel[0];

    if (delCat == null) {
        Ext.Msg.alert('Error', 'Please select the cat to be deleted first!');
        return;
    }

    delCat = delCat.data;

    Ext.Msg.confirm('Delete cat :(', 'Are you sure to delete cat ' + delCat.name + '?', function (button, value) {
        if (button === 'yes') {
            Ext.Ajax.request({
                url: '/api/cat/delete/' + delCat.id,
                method: 'DELETE',
                success: function (response, opts) {
                    Ext.getCmp('catGrid').getStore().remove(sel);
                },
                failure: function (response, opts) {
                    Ext.Msg.alert('Error', 'Error in deleting cat!');
                }
            });
        }
    });
}