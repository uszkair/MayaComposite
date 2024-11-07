package io.axasoft.mayacomposite.mapper;


import io.axasoft.mayacomposite.model.Apartment;
import io.axasoft.mayacomposite.request.ApartmentRequest;
import io.axasoft.mayacomposite.response.ApartmentListResponse;
import io.axasoft.mayacomposite.response.ApartmentResponse;
import org.mapstruct.*;
import java.time.YearMonth;
import java.time.LocalDate;

/**
 * MapStruct mapper interfész a társasház entitás és DTO-k közötti konverzióhoz.
 *
 * @author RobertUszkai
 */
@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = {YearMonth.class, LocalDate.class}
)
public interface ApartmentMapper {

    /**
     * Request objektum konvertálása entitássá
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "address", source = "streetAndNumber")
    @Mapping(target = "dataEntryStartDate", expression = "java(parseYearMonth(request.getDataEntryStartMonth()))")
    @Mapping(target = "residentAccessStartDate", expression = "java(parseYearMonth(request.getResidentAccessStartMonth()))")
    Apartment toEntity(ApartmentRequest request);

    /**
     * Entitás konvertálása response objektummá
     */
    ApartmentResponse toResponse(Apartment apartment);

    /**
     * Entitás konvertálása lista response objektummá
     */
    @Mapping(target = "pinCode", source = "apartmentIdentifier")
    @Mapping(target = "address", expression = "java(buildAddress(apartment))")
    ApartmentListResponse toListResponse(Apartment apartment);

    /**
     * YearMonth string konvertálása LocalDate objektummá
     */
    @Named("parseYearMonth")
    default LocalDate parseYearMonth(String yearMonth) {
        if (yearMonth == null || yearMonth.trim().isEmpty()) {
            return null;
        }
        return YearMonth.parse(yearMonth).atDay(1);
    }

    /**
     * LocalDate formázása YearMonth stringgé
     */
    @Named("formatYearMonth")
    default String formatYearMonth(LocalDate date) {
        if (date == null) {
            return null;
        }
        return YearMonth.from(date).toString();
    }

    /**
     * Teljes cím összeállítása az entitás adataiból
     */
    default String buildAddress(Apartment apartment) {
        if (apartment == null) {
            return null;
        }

        StringBuilder address = new StringBuilder();

        if (apartment.getPostalCode() != null && !apartment.getPostalCode().trim().isEmpty()) {
            address.append(apartment.getPostalCode()).append(" ");
        }

        if (apartment.getCity() != null && !apartment.getCity().trim().isEmpty()) {
            address.append(apartment.getCity());
            if (apartment.getAddress() != null && !apartment.getAddress().trim().isEmpty()) {
                address.append(", ");
            }
        }

        if (apartment.getAddress() != null && !apartment.getAddress().trim().isEmpty()) {
            address.append(apartment.getAddress());
        }

        String result = address.toString().trim();
        return result.isEmpty() ? null : result;
    }
}