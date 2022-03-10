package com.vitocuaderno.skeleton.features.splash

import org.junit.Test

class SplashViewModelTest {
    @Test
    fun shouldEmitIsLoggedIn_false_whenUserSessionDoesNotExist() {
        // TODO
        val expected = SplashState.IsLoggedIn(false)
//        val emptySession = Session(User.empty(), AccessToken())
//
//        whenever(miscellaneousRepository.versionCheck(any()))
//            .thenReturn(Single.just(Stubs.UPDATE_GATE_EMPTY))
//        whenever(sessionRepository.getSession())
//            .thenReturn(Single.just(emptySession))
//
//        subject.isFirstTimeUiCreate(mockBundle)
//
//        testScheduler.triggerActions()
//
//        Mockito
//            .verify(
//                sessionRepository,
//                Mockito.times(1)
//            )
//            .getSession()
//
//        argumentCaptor<SplashState>()
//            .run {
//                Mockito
//                    .verify(
//                        observer,
//                        Mockito.times(1)
//                    )
//                    .onNext(capture())
//
//                Assert.assertEquals(expected, value)
//            }
    }
}
