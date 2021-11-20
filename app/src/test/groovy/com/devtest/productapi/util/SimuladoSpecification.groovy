package com.devtest.productapi.util


import com.github.dockerjava.api.model.ExposedPort
import com.github.dockerjava.api.model.HostConfig
import com.github.dockerjava.api.model.PortBinding
import com.github.dockerjava.api.model.Ports
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.spock.Testcontainers
import org.testcontainers.utility.MountableFile
import spock.lang.Shared

@Testcontainers
abstract class SimuladoSpecification extends BaseSpecification {

    @Shared
    GenericContainer simuladoContainer = new GenericContainer<GenericContainer>("ldabiralai/simulado:latest")
            .withExposedPorts(80)
            .withCopyFileToContainer(MountableFile.forHostPath("../shared/simulado/"), "/app")
            .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
                    new HostConfig()
                            .withPortBindings(new PortBinding(Ports.Binding.bindPort(3001), new ExposedPort(80)))
            ))
            .withCommand("./bin/simulado -f /app/mocks.json")
            .withReuse(true)
            .waitingFor(Wait.forHttp("/"))

}
