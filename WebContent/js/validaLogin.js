$(document).ready(function() {

		$("#form-login").validate({
			rules : {
				'mail' : {
					required : true,
					email : true
				},
				'password' : {
					required : true,
				},
			},
			messages : {
				'mail' : {
					required : "Il campo email è obbligatorio!",
					email : "Inserisci un valido indirizzo email!",
				},
				'password' : {
					required : "Il campo password è obbligatorio!",
				},
			}
		});

	$("#form-iscriviCliente").validate({
			rules : {
				'nome' : {
					required: true,
					minlength: 3
				},
				'cognome' : {
					required: true,
					minlength: 3
				},
				'mail' : {
					required : true,
					email : true,
					remote: {
					    type: 'post',
					    url: 'CheckMail',
					    data: {
					        'mail': function () { return $('#mailClient').val(); }
					    },
					    dataType: 'json'
					}
				},
				'indirizzo':{
					required : true,
					minlength: 3
				},
				'password' : {
					required : true,
					minlength: 8
				},
				'conf-password' : {
					required : true,
					equalTo: '#passClient'
				}

			},
			messages : {
				'nome' : {
					required : "Inserire il proprio nome!",
					minlength: "Inserire un nome valido!"
				},
				'cognome' : {
					required : "Inserire il proprio cognome!",
					minlength: "Inserire un cognome valido!"
				},
				'indirizzo':{
					required : "Il campo email è obbligatorio!",
					minlength: "Il campo email è obbligatorio!"
				},
				'mail' : {
					required : "Il campo email è obbligatorio!",
					email : "Inserisci un valido indirizzo email!",
					remote: "La mail del Cliente è già presente nel Sistema"
				},
				'password' : {
					required : "Il campo password è obbligatorio!",
					minlength: "Inserisci una password di almeno 8 caratteri!"
				},
				'conf-password' : {
					required: "Confermare la password",
					equalTo: "Le due password non coincidono!" 
				}
			}
		});

	$("#form-iscriviProfessionista").validate({
			rules : {
				'nome' : {
					required: true,
					minlength: 3
				},
				'cognome' : {
					required: true,
					minlength: 3
				},
				'professione' : {
					required: true
				},
				'mail' : {
					required : true,
					email : true,
					remote: {
					    type: 'post',
					    url: 'CheckMail',
					    data: {
					        'mail': function () { return $('#mailPro').val(); },
							'professione': function () { return $('#professionePro').val(); }
					    },
					    dataType: 'json'
					}
				},
				'password' : {
					required : true,
					minlength: 8
				},
				'conf-password' : {
					required:true,
					equalTo: '#passPro'
				},

			},
			messages : {
				'nome' : {
					required : "Inserire il proprio nome!",
					minlength: "Inserire un nome valido!"
				},
				'cognome' : {
					required : "Inserire il proprio cognome!",
					minlength: "Inserire un cognome valido!"
				},
				'professione' : {
					required : "Inserire la propria professione!"
				},
				'mail' : {
					required : "Il campo email è obbligatorio!",
					email : "Inserisci un valido indirizzo email!",
					remote: "La mail del Professionista è gia presente nel sistema"
				},
				'password' : {
					required : "Il campo password è obbligatorio!",
					minlength: "Inserisci una password di almeno 8 caratteri!"
				},
				'conf-password' : {
					required: "Confermare la password",
					 equalTo: "Le due password non coincidono!" 
				}
			}
		});
});
