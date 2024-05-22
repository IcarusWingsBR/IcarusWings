function searchPostalCode(value) {
    let postalCode = value.replace(/\D/g, '');

    if (isPostalCodeEmpty(postalCode)) return;
 
    if (!validatePostalCodeFormat(postalCode)) return;

    setInputValuesToPending();

    let script = document.createElement('script');

    script.src = 'https://viacep.com.br/ws/'+ postalCode + '/json/?callback=postalCodeCallback';

    document.body.appendChild(script);
}

function isPostalCodeEmpty(postalCode) {
    if (postalCode != "") return false;

    clearPostalCodeFormat();

    return true;
}

function validatePostalCodeFormat(postalCode) {
    let validatePostalCode = /^[0-9]{8}$/;
    
    if (!validatePostalCode.test(postalCode)) {
        clearPostalCodeFormat();
        alert("Formato de CEP inválido.");

        return false;
    }

    return true;
}

function isContentValid(content) {
    if ("erro" in content) {
        clearPostalCodeFormat();
        alert("CEP não encontrado.");

        return false;
    }

    return true;
}