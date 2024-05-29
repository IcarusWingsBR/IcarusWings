function PostalCodeValidator() {

    this.fetchPostalCode = async function(postalCode) {
        const url = 'https://viacep.com.br/ws/' + postalCode + '/json';

        return fetch(url)
            .then(response => response.json())
            .then(data => {
                return data;
            });
    };

    this.validatePostalCodeFormat = function(postalCode) {
        if (postalCode == "") return false;

        let validatePostalCode = /^[0-9]{8}$/;

        if (!validatePostalCode.test(postalCode)) {
            alert("Formato de CEP inválido.");

            return false;
        }

        return true;
    };

    this.isContentValid = function(response) {
        if ("erro" in response) {
            alert("CEP não encontrado.");

            return false;
        }

        return true;
    };
}