package com.example.janperzrekrutacja.dto.parentchild;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ParentDto {
  private Long id;
  private String firstName;
  private String lastName;

}
