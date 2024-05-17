function searchZipCode(value) {
    let zipCode = value.replace(/\D/g, '')

    if(isZipCodeEmpty(zipCode)) return
 
    if(!validateZipCodeFormat(zipCode)) return

    setInputValuesToPending()

    let script = document.createElement('script')

    script.src = 'https://viacep.com.br/ws/'+ zipCode + '/json/?callback=zipCodeCallback'

    document.body.appendChild(script)
}

function isZipCodeEmpty(zipCode) {
    if(zipCode != "") return false

    clearZipCodeFormat()

    return true
}

function clearZipCodeFormat() {
    document.getElementById('street').value=("")
    document.getElementById('neighborhood').value=("")
    document.getElementById('city').value=("")
    document.getElementById('state').value=("")
}

function validateZipCodeFormat(zipCode) {
    let validacep = /^[0-9]{8}$/ 
    
    if(!validacep.test(zipCode)) {
        clearZipCodeFormat()
        alert("Formato de CEP inválido.")

        return false
    }

    return true
}

function setInputValuesToPending() {
    document.getElementById('street').value="Aguardando"
    document.getElementById('neighborhood').value="Aguardando"
    document.getElementById('city').value="Aguardando"
    document.getElementById('state').value="Aguardando"
}

function zipCodeCallback(content) {
    if(!isContentValid(content)) return

    document.getElementById('street').value=(content.logradouro)
    document.getElementById('neighborhood').value=(content.bairro)
    document.getElementById('city').value=(content.localidade)
    document.getElementById('state').value=(content.uf)

    disableInputs()
}

function isContentValid(content) {
    if("erro" in content) {
        clearZipCodeFormat()
        alert("CEP não encontrado.")

        return false
    }

    return true
}

function disableInputs() {
    document.getElementById('street').readOnly = true
    document.getElementById('neighborhood').readOnly = true
    document.getElementById('city').readOnly = true
    document.getElementById('state').readOnly = true
}
