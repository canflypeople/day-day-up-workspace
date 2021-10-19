package com.zmji;

import com.zmji.mapstruct.entity.Person;
import com.zmji.mapstruct.entity.User;
import com.zmji.mapstruct.vo.PersonDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-24T10:10:19+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11 (Oracle Corporation)"
)
@Component
public class PersonConverterImpl implements PersonConverter {

    @Override
    public PersonDTO domain2dto(Person person) {
        if ( person == null ) {
            return null;
        }

        PersonDTO personDTO = new PersonDTO();

        personDTO.setBirth( person.getBirthday() );
        if ( person.getBirthday() != null ) {
            personDTO.setBirthDateFormat( new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ).format( person.getBirthday() ) );
        }
        personDTO.setAge( personUserAge( person ) );
        personDTO.setId( person.getId() );
        personDTO.setName( person.getName() );

        personDTO.setBirthExpressionFormat( org.apache.commons.lang3.time.DateFormatUtils.format(person.getBirthday(),"yyyy-MM-dd HH:mm:ss") );

        return personDTO;
    }

    @Override
    public List<PersonDTO> domain2dto(List<Person> people) {
        if ( people == null ) {
            return null;
        }

        List<PersonDTO> list = new ArrayList<PersonDTO>( people.size() );
        for ( Person person : people ) {
            list.add( domain2dto( person ) );
        }

        return list;
    }

    @Override
    public void update(Person person, PersonDTO personDTO) {
        if ( person == null ) {
            return;
        }

        personDTO.setBirth( person.getBirthday() );
        if ( person.getBirthday() != null ) {
            personDTO.setBirthDateFormat( new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ).format( person.getBirthday() ) );
        }
        else {
            personDTO.setBirthDateFormat( null );
        }
        personDTO.setAge( personUserAge( person ) );
        personDTO.setId( person.getId() );
        personDTO.setName( person.getName() );

        personDTO.setBirthExpressionFormat( org.apache.commons.lang3.time.DateFormatUtils.format(person.getBirthday(),"yyyy-MM-dd HH:mm:ss") );
    }

    private Integer personUserAge(Person person) {
        if ( person == null ) {
            return null;
        }
        User user = person.getUser();
        if ( user == null ) {
            return null;
        }
        Integer age = user.getAge();
        if ( age == null ) {
            return null;
        }
        return age;
    }
}
