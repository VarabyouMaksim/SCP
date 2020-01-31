package com.leverx.sample.other;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SpyTest {
	
	@Spy
	private List<String> spyList = new ArrayList<String>();
	
	@Mock
	private List<String> mockList = new ArrayList<String>();
	
	@Captor
	private ArgumentCaptor<String> argCaptor;
	
	@Test
	public void checkRefrigeratorId_givenSpyList_shouldBeEqual() {
		spyList.add("firstValue");
		assertEquals(spyList.size(), 1); // 1 element has been added -> Spy
	}
	
	@Test
	public void checkRefrigeratorId_givenMockList_shouldBeEqual() {
		mockList.add("firstValue");
		assertEquals(mockList.size(), 0); // 0 elements have been added -> Mock
	}
	
	@Test
	public void checkCapturedValue_givenMockList_shouldBeEqual() {
		mockList.add("firstValue");
		verify(mockList).add(argCaptor.capture());
		assertEquals("firstValue", argCaptor.getValue());
	}
	
}
