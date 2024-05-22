function clearPostalCodeFormat() {
    document.getElementById('street').value=("");
    document.getElementById('neighborhood').value=("");
    document.getElementById('city').value=("");
    document.getElementById('state').value=("");
}

function setInputValuesToPending() {
    document.getElementById('street').value="Aguardando";
    document.getElementById('neighborhood').value="Aguardando";
    document.getElementById('city').value="Aguardando";
    document.getElementById('state').value="Aguardando";
}

function postalCodeCallback(content) {
    if (!isContentValid(content)) return;

    document.getElementById('street').value=(content.logradouro);
    document.getElementById('neighborhood').value=(content.bairro);
    document.getElementById('city').value=(content.localidade);
    document.getElementById('state').value=(content.uf);

    disableInputs();
}

function disableInputs() {
    document.getElementById('street').readOnly = true;
    document.getElementById('neighborhood').readOnly = true;
    document.getElementById('city').readOnly = true;
    document.getElementById('state').readOnly = true;
}