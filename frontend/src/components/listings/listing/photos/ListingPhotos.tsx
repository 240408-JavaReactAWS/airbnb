interface IListingPhotoProps {
    listingId: number,
    photos: string[],
    alt: string
}

function ListingsPhotos(props: IListingPhotoProps) {
    return (
        <div id={`carouselExample-${props.listingId}`} className="carousel slide">
            <div className="carousel-inner">
                {props.photos.length > 0 && props.photos.map((photo) => (
                    <div className="carousel-item active" key={photo}>
                        <img src={photo} alt={props.alt} className="d-block w-100" />
                    </div>
                ))}
            </div>
            <button className="carousel-control-prev" type="button" data-bs-target={`#carouselExample-${props.listingId}`} data-bs-slide="prev" >
                <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                <span className="visually-hidden">Previous</span>
            </button>
            <button className="carousel-control-next" type="button" data-bs-target={`#carouselExample-${props.listingId}`} data-bs-slide="next">
                <span className="carousel-control-next-icon" aria-hidden="true"></span>
                <span className="visually-hidden">Next</span>
            </button>
        </div>
    );
}

export default ListingsPhotos;