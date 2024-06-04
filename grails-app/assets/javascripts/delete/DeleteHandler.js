function DeleteHandler() {

    this.fetchDelete = async function(domain, id) {
        const url =  `/${domain}/delete/${id}`;

        return fetch(url, {
            method: 'DELETE'
        })
        .then(response => {
            if(response.ok) return response.text();

            throw new Error('Failed to delete');
        })
    };
}