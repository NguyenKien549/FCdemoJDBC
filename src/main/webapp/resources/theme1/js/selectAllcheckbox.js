$('#select_all').change(function() {
			    var checkboxes = $(this).closest('form').find(':checkbox'); /* co the tuy chinh theo block code khac nhau vi du thay form bang table */
			    checkboxes.prop('checked', $(this).is(':checked'));
			});