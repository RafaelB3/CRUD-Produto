$('#ModalExclusao').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);

	var codigoProduto = button.data('codigo');
	var descricaoProduto = button.data('descricao')


	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}

	form.attr('action', action + codigoProduto);

	modal.find('.modal-body span').html('Tem certeza que deseja excluir o produto <strong>' + descricaoProduto + '</strong>?');
});

$(function() {
	$('[rel="tooltip"]').tooltip();
	$('.js-currency').maskMoney({ decimal: ',', thousands: '.', allowZero: true });


})