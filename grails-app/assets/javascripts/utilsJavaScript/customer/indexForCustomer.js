function clearPostalCodeFormat() {
    document.getElementById('address').value=("");
    document.getElementById('province').value=("");
    document.getElementById('city').value=("");
    document.getElementById('state').value=("");
}

function setInputValuesToPending() {
    document.getElementById('address').value="Aguardando";
    document.getElementById('province').value="Aguardando";
    document.getElementById('city').value="Aguardando";
    document.getElementById('state').value="Aguardando";
}

function postalCodeCallback(content) {
    if (!isContentValid(content)) return;

    document.getElementById('address').value=(content.logradouro);
    document.getElementById('province').value=(content.bairro);
    document.getElementById('city').value=(content.localidade);
    document.getElementById('state').value=(content.uf);

    disableInputs();
}

function disableInputs() {
    document.getElementById('address').readOnly = true;
    document.getElementById('province').readOnly = true;
    document.getElementById('city').readOnly = true;
    document.getElementById('state').readOnly = true;
}