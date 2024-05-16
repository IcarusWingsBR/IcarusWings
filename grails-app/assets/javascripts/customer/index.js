function desabilita_inputs() {
    document.getElementById('street').readOnly = true
    document.getElementById('neighborhood').readOnly = true
    document.getElementById('city').readOnly = true
    document.getElementById('state').readOnly = true
}

function limpa_formulário_cep() {
    document.getElementById('street').value=("")
    document.getElementById('neighborhood').value=("")
    document.getElementById('city').value=("")
    document.getElementById('state').value=("")
}

function cep_callback(conteudo) {
    if (!("erro" in conteudo)) {
        document.getElementById('street').value=(conteudo.logradouro)
        document.getElementById('neighborhood').value=(conteudo.bairro)
        document.getElementById('city').value=(conteudo.localidade)
        document.getElementById('state').value=(conteudo.uf)

        desabilita_inputs();
    }
    else {
        limpa_formulário_cep();
        alert("CEP não encontrado.")
    }
}

function pesquisacep(valor) {

    var cep = valor.replace(/\D/g, '')

    if (cep != "") {

        var validacep = /^[0-9]{8}$/

        if(validacep.test(cep)) {

            document.getElementById('street').value="..."
            document.getElementById('neighborhood').value="..."
            document.getElementById('city').value="..."
            document.getElementById('state').value="..."

            var script = document.createElement('script')

            script.src = 'https://viacep.com.br/ws/'+ cep + '/json/?callback=cep_callback'

            document.body.appendChild(script)

        }
        else {
            limpa_formulário_cep();
            alert("Formato de CEP inválido.")
        }
    }
    else {
        limpa_formulário_cep()
    }
}