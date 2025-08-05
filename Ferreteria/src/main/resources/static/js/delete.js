document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll('.delete-form').forEach(form => {
        form.addEventListener('submit', function (e) {
            e.preventDefault();

            Swal.fire({
                title: '¿Estás seguro?',
                text: 'Esta acción eliminará el cliente de forma permanente.',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#d33',
                cancelButtonColor: '#3085d6',
                confirmButtonText: 'Sí, eliminar',
                cancelButtonText: 'Cancelar',
                customClass: {
                    popup: 'rounded-2xl',
                    title: 'text-xl font-bold',
                    confirmButton: 'bg-red-600 text-white px-4 py-2 rounded hover:bg-red-700',
                    cancelButton: 'bg-gray-300 text-black px-4 py-2 rounded hover:bg-gray-400'
                },
                buttonsStyling: false
            }).then((result) => {
                if (result.isConfirmed) {
                    form.submit();
                }
            });
        });
    });
});
