package gossamer

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.services.ec2.AmazonEC2Client
import com.amazonaws.services.ec2.model.DescribeImagesRequest
import com.amazonaws.services.ec2.model.DescribeInstancesRequest
import com.amazonaws.services.ec2.model.Filter
import groovy.json.JsonBuilder
import io.snowcrash.gossamer.aws.FilterTypes

class InfrastructureService {

    def getStack(String name, String region) {
        def amazonEC2Client = new AmazonEC2Client(new DefaultAWSCredentialsProviderChain())
        amazonEC2Client.endpoint = "ec2.${region}.amazonaws.com"

        def describeImagesRequest = new DescribeInstancesRequest()
        def filter = new Filter(FilterTypes.STACK.displayName)
        filter.values = [name]

        describeImagesRequest.withFilters([filter])
        return amazonEC2Client.describeInstances(describeImagesRequest).reservations*.instances
    }

    def describe() {

    }

    def ec2() {}

    def ecs() {}

    def responseTimes() {}
}
