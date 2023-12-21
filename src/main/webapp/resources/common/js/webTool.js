window.WebTool = {
	submit: function (formData) {
		if (!(formData['action'])) {
			alert('action 값은 필수입니다');
			return;
		}

		let $form = $('<form></form>');
		$form.attr('action', formData['action']);
		$form.attr('method', formData['method'] || 'post');
		$form.attr('target', formData['target'] || '_self');	// _target, _blank

		/*
		 * 'enctype' : 'multipart/form-data'
		 * 'accept-charset' : 'EUC-KR'
		 */
		let etcAttrs = formData['etcAttrs'] || {};
		for (var key in etcAttrs) {
			$form.attr(key, etcAttrs[key]);
		}

		$form.appendTo('body');

		let params = formData['params'];
		for (let key in params) {
			$form.append($('<input type="hidden" name="' + key + '" />'));
			$form.find('INPUT[name=' + key + ']').val(params[key]);
		}

		$form.submit();

		$form.remove();
	},
}