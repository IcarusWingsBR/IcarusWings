function RestoreHandler() {

    this.fetchRestore = async function(domain, id) {
        const url =  `/${domain}/restore/${id}`;

        return fetch(url, {
            method: 'POST'
        })
        .then(response => {
            if(response.ok) return response.text();

            if(response.redirected) return "Redirected.";

            throw new Error('Failed to delete');
        })
    };
}