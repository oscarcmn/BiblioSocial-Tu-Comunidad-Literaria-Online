export interface GoogleBooksAPIResponse {
  kind: string;
  totalItems: number;
  items: GoogleBook[];
}

export interface GoogleBook {
  kind: string;
  id: string;
  etag: string;
  selfLink: string;
  volumeInfo: VolumeInfo;
  saleInfo: SaleInfo;
  accessInfo: AccessInfo;
  searchInfo?: SearchInfo;
}

export interface VolumeInfo {
  title: string;
  subtitle?: string;
  authors?: string[];
  publisher?: string;
  publishedDate?: string;
  description?: string;
  industryIdentifiers?: IndustryIdentifier[];
  pageCount?: number;
  categories?: string[];
  averageRating?: number;
  ratingsCount?: number;
  maturityRating?: string;
  language?: string;
  previewLink?: string;
  infoLink?: string;
  canonicalVolumeLink?: string;
  imageLinks?: {
    smallThumbnail?: string;
    thumbnail?: string;
  };
}

export interface IndustryIdentifier {
  type: string;
  identifier: string;
}

export interface SaleInfo {
  country?: string;
  saleability: 'FOR_SALE' | 'NOT_FOR_SALE' | 'FREE' | 'FOR_RENTAL_ONLY' | 'FOR_PREORDER';
  isEbook?: boolean;
  listPrice?: {
    amount: number;
    currencyCode: string;
  };
  retailPrice?: {
    amount: number;
    currencyCode: string;
  };
  buyLink?: string;
}

export interface AccessInfo {
  country?: string;
  viewability?: string;
  embeddable?: boolean;
  publicDomain?: boolean;
  textToSpeechPermission?: string;
  epub: {
    isAvailable: boolean;
    downloadLink?: string;
  };
  pdf: {
    isAvailable: boolean;
    downloadLink?: string;
  };
  webReaderLink?: string;
}

export interface SearchInfo {
  textSnippet?: string;
}

  
